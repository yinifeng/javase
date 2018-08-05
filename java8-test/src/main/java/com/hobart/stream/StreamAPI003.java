package com.hobart.stream;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

import com.hobart.model.Employee;
import com.hobart.model.Employee.Status;

public class StreamAPI003 {
	List<Employee> emps=Arrays.asList(
			new Employee("张三", 18, 9999.99,Status.FREE),
			new Employee("李四", 40, 3333.33,Status.BUSY),
			new Employee("王五", 50, 5555.55,Status.VOCATION),
			new Employee("赵六", 30, 8888.88,Status.FREE),
			new Employee("田七", 15, 7777.77,Status.BUSY)
	);
	
	
	//收集处理之特殊处理
	@Test
	public void test9(){
		DoubleSummaryStatistics dss = emps.stream()
			.collect(Collectors.summarizingDouble(Employee::getSalary));
		System.out.println(dss.getAverage());//平均值
		System.out.println(dss.getMax());//最大值
		System.out.println(dss.getMin());//最小值
		System.out.println(dss.getSum());//求和
		System.out.println("--------------------------------");
		//字符拼接
		String str = emps.stream()
			.map(Employee::getName)
			.collect(Collectors.joining(","));
		System.out.println(str);
	}
	
	//收集处理之 分片 或 分区
	@Test
	public void test8(){
		Map<Boolean, List<Employee>> map = emps.stream()
			.collect(Collectors.partitioningBy((e)->e.getSalary()>8000));
		System.out.println(map);
	}
	
	//收集之多级分组
	@Test
	public void test7(){
		Map<Status, Map<String, List<Employee>>> map = emps.stream()
			.collect(Collectors.groupingBy(Employee::getStatu,Collectors.groupingBy((e)->{
				if(((Employee)e).getAge()<=35){
					return "青年";
				}else if(((Employee) e).getAge()<=50){
					return "中年";
				}else{
					return "老年";
				}
			})));
		System.out.println(map);
	}
	
	//收集之分组
	@Test
	public void test6(){
		//分组
		Map<Status, List<Employee>> group = emps.stream()
			.collect(Collectors.groupingBy(Employee::getStatu));
		System.out.println(group);
	}
	
	//收集处理
	@Test
	public void test5(){
		//总数
		Long count = emps.stream()
			.collect(Collectors.counting());
		System.out.println(count);
		System.out.println("--------------------------------");
		//平均值
		Double avg = emps.stream()
			.collect(Collectors.averagingDouble(Employee::getSalary));
		System.out.println(avg);
		System.out.println("--------------------------------");
		//总和
		Double sum = emps.stream()
			.collect(Collectors.summingDouble(Employee::getSalary));
		System.out.println(sum);
		System.out.println("--------------------------------");
		//最大值
		Optional<Employee> max = emps.stream()
			.collect(Collectors.maxBy((e1,e2)->Double.compare(e1.getSalary(), e2.getSalary())));
		System.out.println(max.get());
		System.out.println("--------------------------------");
		//最小值
		Optional<Double> min = emps.stream()
			.map(Employee::getSalary)
			.collect(Collectors.minBy(Double::compare));
		System.out.println(min.get());
	}
	
	@Test
	public void test4(){
		//员工名字收集到List中
		List<String> list = emps.stream()
			.map(Employee::getName)
			.collect(Collectors.toList());//Collectors工具类构造实现
		list.forEach(System.out::println);
		System.out.println("-------------------------");
		//员工名字收集到Set中
		Set<String> set = emps.stream()
			.map(Employee::getName)
			.collect(Collectors.toSet());
		set.forEach(System.out::println);
		System.out.println("------------------------");
		//员工名字收集到指定集合中
		HashSet<String> hashset = emps.stream()
			.map(Employee::getName)
			.collect(Collectors.toCollection(HashSet::new));
		hashset.forEach(System.out::println);
	}
	
	@Test
	public void test3(){
		List<Integer> list=Arrays.asList(1,2,3,4,5,6,7,8,9);
		Integer sum = list.stream()
			.reduce(0, (x,y)->x+y);//求和上面的元素集合
		System.out.println(sum);
		System.out.println("------------------");
		//求出所有员工工资
		Optional<Double> op = emps.stream()
			.map(Employee::getSalary)
			.reduce(Double::sum);
		System.out.println(op.get());
	}
	
	@Test
	public void test2(){
		long count = emps.stream()
			.count();
		System.out.println(count);
		
		Optional<Employee> op1 = emps.stream()
			.max((e1,e2)->Double.compare(e1.getSalary(), e2.getSalary()));
		System.out.println(op1.get());
		
		Optional<Double> min = emps.stream()
			.map(Employee::getSalary)
			.min(Double::compare);
		System.out.println(min.get());
	}
	
	//终止操作
	@Test
	public void test1(){
		boolean b1 = emps.stream()
			.allMatch((e) -> e.getStatu()==Status.BUSY);
		System.out.println(b1);
		
		boolean b2 = emps.stream()
			.anyMatch((e) -> e.getStatu()==Status.BUSY);
		System.out.println(b2);
		
		boolean b3 = emps.stream()
			.noneMatch((e) -> e.getStatu()==Status.BUSY);
		System.out.println(b3);
		
		Optional<Employee> op = emps.stream()
			.sorted((e1,e2)->Double.compare(e1.getSalary(), e2.getSalary()))
			.findFirst();
		System.out.println(op.get());
		
		Optional<Employee> op2 = emps.stream()
			.filter((e)->e.getStatu()==Status.BUSY)
			.findAny();
		System.out.println(op2.get());
	}
}
 