package com.hobart.stream;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

import org.junit.Test;

public class TestForkJoin {
	
	@Test
	public void test1(){
		Instant start = Instant.now();
		ForkJoinPool pool=new ForkJoinPool();
		ForkJoinTask<Long> task=new ForkJoinCalculate(0, 100000000000L);
		Long sum = pool.invoke(task);
		System.out.println(sum);//耗时：16704
		
		Instant end = Instant.now();
		System.out.println("耗时："+Duration.between(start, end).toMillis());
	}
	
	@Test
	public void test2(){
		Instant start = Instant.now();
		long sum=0L;
		for(long i=0;i<=100000000000L;i++){
			sum +=i;
		}
		System.out.println(sum);//耗时：31253
		
		Instant end = Instant.now();
		System.out.println("耗时："+Duration.between(start, end).toMillis());
	}
	
	/**
	 * java8 并行流
	 */
	@Test
	public void test3(){
		Instant start = Instant.now();
		LongStream.range(0, 100000000000L)
				  .parallel()//并行流
				  .reduce(0,Long::sum);
		Instant end = Instant.now();//11318
		System.out.println("耗时："+Duration.between(start, end).toMillis());
	}
}
