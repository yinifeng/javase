package com.hobart.lambda;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.junit.Test;

import com.hobart.model.Employee;

public class TestMethodRef {
	
	//数组引用Type::new
	@Test
	public void test7(){
		Function<Integer, String[]> fun=(x) -> new String[x];
		System.out.println(fun.apply(10).length);
		
		Function<Integer, String[]> fun2=String[]::new;
		System.out.println(fun2.apply(20).length);
	}
	
	//构造器应用
	@Test
	public void test5(){
		Supplier<Employee> sup=() -> new Employee();
		//构造器应用方式
		Supplier<Employee> sup2=Employee::new;
		Employee emp=sup2.get();//使用的无参构造器
		System.out.println(emp);
	}
	
	@Test
	public void test6(){
		Function<Integer, Employee> fun=(x) -> new Employee(x);
		
		Function<Integer, Employee> fun2=Employee::new;
		Employee emp = fun2.apply(101);//根据抽象方法的参数调用不同的构造
		System.out.println(emp);
		
		BiFunction<Integer, Integer, Employee> bf=Employee::new;
		Employee emp2 = bf.apply(102, 18);//根据抽象方法的参数调用不同的构造
		System.out.println(emp2);
	}
	
	//类::实例方法名
	@Test
	public void test4(){
		//Lambda表达式
		BiPredicate<String, String> bp=(x,y) -> x.equals(y);
		//类::实例方法名
		//第一个参数为方法的调用者第二个参数为方法的参数，那么就可以类::实例方法名
		BiPredicate<String, String> bp1=String::equals;
	}
	
	//类::静态方法名
	@Test
	public void test3(){
		Comparator<Integer> com=(x,y) -> Integer.compare(x, y);
		
		Comparator<Integer> com1=Integer::compare;
	}
	
	//对象::实例方法名
	@Test
	public void test1(){
		PrintStream ps = System.out;
		Consumer<String> con=(x) -> ps.println(x);
		con.accept("hello");
		
		Consumer<String> con2=ps::println;
		con2.accept("方法引用");
		
		//抽象方法的参数类型必须和方法引用的参数类型一致
		Consumer<String> con3=System.out::println;
		con3.accept("abcd");
	}
	
	@Test
	public void test2(){
		Employee emp=new Employee();
		Supplier<String> sup=() -> emp.getName();
		String name = sup.get();
		System.out.println(name);
		
		Supplier<Integer> sup2=emp :: getAge;
		System.out.println(sup2.get());
	}
}
