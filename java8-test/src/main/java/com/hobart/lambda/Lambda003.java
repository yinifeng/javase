package com.hobart.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.Test;

/**
 * 	Consumer<T> : 消费型接口
 *
 *		void accept(T t);
 *
 *	Supplier<T> : 供给型接口
 *
 *		T get();
 *
 *	Function<T,R> : 函数型接口
 *
 *		R apply(T t);
 *
 *	Predicate<T> : 断言型接口
 *
 *		boolean test(T t);
 *
 * @author hobart
 *
 */
public class Lambda003 {
	
	//Predicate<T> : 断言型接口
	@Test
	public void test4(){
		List<String> list=Arrays.asList("hubo","java","lambda","www","ok");
		List<String> list2 = filterStr(list, (x) -> x.length()>3);
		for(String s:list2){
			System.out.println(s);
		}
	}
	
	//将满足条件的字符串放入到集合中
	public List<String> filterStr(List<String> list,Predicate<String> pre){
		List<String> newList=new ArrayList<>();
		for(String str:list){
			if(pre.test(str)){
				newList.add(str);
			}
		}
		return newList;
	}
	
	
	//Function<T,R> : 函数型接口
	@Test
	public void test3(){
		String str = strHandler("\t\t\t 我爱中国 ", (s) -> s.trim());
		System.out.println(str);
		System.out.println("-------------------------------------");
		String str2 = strHandler("我爱中国", (s) -> s.substring(2, s.length()));
		System.out.println(str2);
	}
	
	//用于处理字符串
	public String strHandler(String str,Function<String, String> fun){
		return fun.apply(str);
	}
	
	//Supplier<T> : 供给型接口
	@Test
	public void test2(){
		List<Integer> numList = getNumList(10,() -> (int)(Math.random()*100));
		for(Integer i:numList){
			System.out.println(i);
		}
	}
	
	//产生指定个数的整数
	public List<Integer> getNumList(int num,Supplier<Integer> sup){
		List<Integer> list=new ArrayList<>();
		for(int i=0 ; i < num ;++i){
			list.add(sup.get());
		}
		return list;
	}
	
	//Consumer<T> : 消费型接口
	@Test
	public void test1(){
		consumer(10000.0, (x) -> System.out.println("旅游消费"+x+"元"));
	}
	
	public void consumer(double money,Consumer<Double> con){
		con.accept(money);
	}
}
