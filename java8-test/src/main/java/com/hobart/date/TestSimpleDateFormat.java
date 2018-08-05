package com.hobart.date;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

public class TestSimpleDateFormat {

	@Test
	public void test1() throws Exception {
		Callable<Date> task = new Callable<Date>() {
			@Override
			public Date call() throws Exception {
				return DateFormatThreadLocal.convert("20180326");
			}
		};

		ExecutorService pool = Executors.newFixedThreadPool(10);
		List<Future<Date>> list = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			list.add(pool.submit(task));
		}

		for (Future<Date> f : list) {
			System.out.println(f.get());
		}

		pool.shutdown();
	}

	@Test
	public void test() throws InterruptedException, ExecutionException {

		// 报错存在线程安全问题
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Callable<Date> task = new Callable<Date>() {
			@Override
			public Date call() throws Exception {
				return sdf.parse("20180326");
			}
		};

		ExecutorService pool = Executors.newFixedThreadPool(10);
		List<Future<Date>> list = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			list.add(pool.submit(task));
		}

		for (Future<Date> f : list) {
			System.out.println(f.get());
		}

		pool.shutdown();
	}

}
