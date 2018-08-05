package com.hobart.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

import com.hobart.model.Employee;

public class StreamAPI002 {
	
	List<Employee> emps=Arrays.asList(
			new Employee("张三", 18, 9999.99),
			new Employee("李四", 40, 3333.33),
			new Employee("王五", 50, 5555.55),
			new Employee("赵六", 30, 8888.88),
			new Employee("田七", 15, 7777.77),
			new Employee("田七", 15, 7777.77),
			new Employee("田七", 15, 7777.77),
			new Employee("田七", 15, 7777.77)
			
	);
	
	//排序
	@Test
	public void test6(){
		List<String> list=Arrays.asList("ccc","aaa","bbb","ddd","eee");
		list.stream()
			.sorted()
			.forEach(System.out::println);
		System.out.println("------------------------------");
		emps.stream()
			.sorted((e1,e2) ->{
				if(e1.getName().equals(e2.getName())){
					return e1.getAge()-e2.getAge();
				}else{
					return e1.getName().compareTo(e2.getName());
				}
			}).forEach(System.out::println);
	}
	
	//map映射 提取每个元素执行相应方法
	@Test
	public void test5(){
		List<String> list=Arrays.asList("aaa","bbb","ccc","ddd","eee");
		list.stream()
			.map((str) -> str.toUpperCase())//转换大小写
			.forEach(System.out::println);
		
		System.out.println("-------------------------------");
		emps.stream()
			.map(Employee::getName)//提取名字
			.forEach(System.out::println);
		System.out.println("--------------------------------");
		Stream<Stream<Character>> sm = list.stream()
			.map(StreamAPI002::filterCharacter);//{{a,a,a},{b,b,b}...}
		sm.forEach((s) -> {
			s.forEach(System.out::println);
		});
		
		//map 和 flatmap 类似集合 的add 和 addAll方法
		System.out.println("----------------------------------");
		list.stream()
			.flatMap(StreamAPI002::filterCharacter)
			.forEach(System.out::println);//{a,a,a,b,b,b...}
		
	}
	
	public static Stream<Character> filterCharacter(String str){
		List<Character> list=new ArrayList<>();
		for(Character c:str.toCharArray()){
			list.add(c);
		}
		return list.stream();
	}
	
	//skip 跳过前几个
	//distinct 去重
	@Test
	public void test4(){
		emps.stream()
			.filter((e) -> e.getSalary()>5000)
			.skip(2)
			.distinct()
			.forEach(System.out::println);
	}
	
	//limit
	@Test
	public void test3(){
		emps.stream()
			.filter((e) -> {
				System.out.println("短路!");
				return e.getAge()>35;})
			.limit(2)//查找到2个立即退出循环
			.forEach(System.out::println);
	}
	
	//中间操作：
	//内部迭代由Stream API完成
	@Test
	public void test1(){
		//中间操作：不会执行任何操作
		Stream<Employee> stream = emps.stream()
			.filter((e) ->{
				System.out.println("Stream API 的中间操作");
				return e.getAge()>35;
			});
		//终止操作：一次性执行全部内容，即“惰性求值”
		stream.forEach(System.out::println);
	}
	//外部迭代
	@Test
	public void test2(){
		Iterator<Employee> it = emps.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}
}
