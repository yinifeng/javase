package com.hobart.optional;

import java.util.Optional;

import org.junit.Test;

import com.hobart.model.Employee;
import com.hobart.model.Employee.Status;

/**
 * Optional 测试
 * @author hobart
 *
 */
public class OptionalTest {
	
	
	@Test
	public void test5(){
		Optional<NewMan> op = Optional.ofNullable(new NewMan(Optional.ofNullable(new Godness("梁静茹"))));
		String name = getGodness(op);
		System.out.println(name);
	}
	
	public String getGodness(Optional<NewMan> man){
		return man.orElse(new NewMan())
				  .getGodness()
				  .orElse(new Godness("田馥甄"))
				  .getName();
	}
	
	@Test
	public void test4(){
		Optional<Employee> op = Optional.ofNullable(new Employee("张三", 28, 8888.88, Status.FREE));
		Optional<String> name = op.map(Employee::getName);
		System.out.println(name.get());
		
		Optional<String> map = op.flatMap((e)->Optional.of(e.getName()));
		System.out.println(map.get());
	}
	
	@Test
	public void test3(){
		Optional<Employee> op = Optional.ofNullable(new Employee());
		if(op.isPresent()){//判断是否有值
			System.out.println(op.get());
		}
		Employee emp = op.orElse(new Employee(1, 28));//有值使用默认的，没值构建一个
		System.out.println(emp);
		
		Employee emp2 = op.orElseGet(()->new Employee());//可以Lambda表达式构建对象
		System.out.println(emp2);
	}
	
	@Test
	public void test2(){
		Optional<Employee> op = Optional.empty();
		System.out.println(op.get());
	}
	
	@Test
	public void test1(){
		Optional<Employee> op = Optional.of(null);
		System.out.println(op.get());
	}
}
