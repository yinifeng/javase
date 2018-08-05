package com.hobart.juc.pool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestScheduledExcutor {
	public static void main(String[] args) {
		//周期执行任务的线程池
		final ScheduledExecutorService pool = Executors.newScheduledThreadPool(10);
		
		/**
		 * initialDelay:首次运行的时间 10秒之后运行
		 * period：执行的时间间隔
		 * TimeUnit.SECONDS:秒
		 */
		pool.scheduleAtFixedRate(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("hello"+System.currentTimeMillis());
				
			}
		}, 10, 10, TimeUnit.SECONDS);
	}
}
