package com.hobart.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

import com.hobart.model.Employee;

public class Lambda001 {
	
	//匿名类部类
	@Test
	public void test1(){
		Comparator<Integer> com=new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return Integer.compare(o1, o2);//一句代码关键逻辑
			}
		};
		Set<Integer> set=new TreeSet<>(com);
	}
	
	//Lambda表达式
	@Test
	public void test2(){
		Comparator<Integer> com=(x,y) -> Integer.compare(x, y);
		Set<Integer> set=new TreeSet<>(com);
	}
	
	
	List<Employee> emps=Arrays.asList(
			new Employee("张三", 18, 9999.99),
			new Employee("李四", 40, 3333.33),
			new Employee("王五", 50, 5555.55),
			new Employee("赵六", 30, 8888.88),
			new Employee("田七", 15, 7777.77)
			
	);
	
	//需求：过滤集合中年龄大于35的员工
	@Test
	public void test3(){
		List<Employee> list = filterEmployees(emps);
		for(Employee emp:list){
			System.out.println(emp);
		}
	}
	
	public List<Employee> filterEmployees(List<Employee> list){
		List<Employee> emps=new ArrayList<>();
		for(Employee emp:list){
			if(emp.getAge()>35)
				emps.add(emp);
		}
		return emps;
	}
	
	//需求：过滤集合中工资大于5000的员工
	public List<Employee> filterEmployees2(List<Employee> list){
		List<Employee> emps=new ArrayList<>();
		for(Employee emp:list){
			if(emp.getSalary()>5000)
				emps.add(emp);
		}
		return emps;
	}
	
	//以上方式能达到需求但是冗余代码太多
	
	//优化方式一：策略设计模式
	//定义一个接口
	@Test
	public void test4(){
		List<Employee> list = filterEmployees(emps,new FilterEmployeeByAge());
		for(Employee emp:list){
			System.out.println(emp);
		}
		
		System.out.println("---------------------------------------------");
		List<Employee> list2 = filterEmployees(emps,new FilterEmployeeBySalary());
		for(Employee emp:list2){
			System.out.println(emp);
		}
	}
	
	public List<Employee> filterEmployees(List<Employee> list,MyPredicate<Employee> mp){
		List<Employee> emps=new ArrayList<>();
		for(Employee emp:list){
			if(mp.test(emp))
				emps.add(emp);
		}
		return emps;
	}
	
	//优化方式二：匿名内部类
	@Test
	public void test5(){
		List<Employee> list = filterEmployees(emps,new MyPredicate<Employee>() {
			
			@Override
			public boolean test(Employee t) {
				return t.getSalary() <= 5000;
			}
		});
		
		for(Employee emp:list){
			System.out.println(emp);
		}
	}
	
	//优化方式三：Lambda表达式
	@Test
	public void test6(){
		List<Employee> list = filterEmployees(emps, (e) -> e.getSalary() <= 5000);
		list.forEach(System.out :: println);
	}
	
	
	//优化方式四：Stream API
	@Test
	public void test7(){
		emps.stream()
			.filter((e) -> e.getSalary()>=5000)
			.limit(2)
			.forEach(System.out :: println);
		
		System.out.println("----------------------");
		
		emps.stream()
			.map(Employee :: getName)
			.forEach(System.out :: println);
	}
}
