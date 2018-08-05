package com.hobart.lambda;

import java.util.Comparator;
import java.util.function.Consumer;

import org.junit.Test;

/*java8引入了一个新的操作符 “->” 该操作符称为箭头操作符 或 Lambda 操作符
 *
 *箭头操作符将 Lambda 表达式拆分成两部分：
 *
 *左侧：Lambda 表达式的参数列表
 *
 *右侧：Lambda 表达式中所需执行的功能，即 Lambda 体
 *
 *语法格式一：无参数，无返回值
 */
public class Lambda002 {
	
	@Test
	public void test1(){
		final int num=0;//jdk1.7 要加final
		
		Runnable r1 = new Runnable() {
			@Override
			public void run() {
				System.out.println("Hello Wolrd!"+num);
			}
		};
		
		r1.run();
		System.out.println("------------------------");
		Runnable r2=() -> System.out.println("Hello Wolrd!");
		r2.run();
	}
	
	@Test
	public void test2(){
		Consumer<String> con=(x) -> System.out.println(x);
		con.accept("爱我中华");
		
		//也可以省略小括号
		Consumer<String> con1=x -> System.out.println(x);
		con1.accept("爱我中华");
	}
	
	@Test
	public void test3(){
		Comparator<Integer> com=(x,y) -> {
			System.out.println("函数式接口");
			return Integer.compare(x, y);
		};
		
		//只有一条语句都可以省略不写
		Comparator<Integer> com2=(x,y) -> Integer.compare(x, y);
	}
	
	//需求：对一个数进行计算
	@Test
	public void test4(){
		Integer num=operation(100, (x) -> x+x);
		System.out.println(num);
		
		System.out.println("-------------");
		Integer num2=operation(100, (x) -> x*x);
		System.out.println(num2);
	}
	
	public Integer operation(Integer num,MyFun mf){
		return mf.getValue(num);
	}
}
