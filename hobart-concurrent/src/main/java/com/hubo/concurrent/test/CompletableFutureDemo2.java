package com.hubo.concurrent.test;

import java.util.Arrays;
import java.util.concurrent.*;

/**
 * 多线程处理任务，当其中某个线程发生异常，其他正在执行的线程发送中断
 */
public class CompletableFutureDemo2 {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        CompletableFuture<?>[] futures = new CompletableFuture[10];
        for (int i = 1; i <= 10 ; i++) {
            int a = i;
            CompletableFuture<?> future = CompletableFuture.runAsync(() -> {
                System.out.printf("[%s]->[%d]任务开始执行.....%n",Thread.currentThread().getName(), a);
                if (a > 2) {
                    if (a == 3) {
                        try {
                            TimeUnit.SECONDS.sleep(3);
                        } catch (InterruptedException e) {
                            throw new RuntimeException("3执行异常");
                        }
                        throw new RuntimeException("3执行异常");
                    }else {
                        try {
                            TimeUnit.SECONDS.sleep(a * 10);
                        } catch (InterruptedException e) {
                            System.out.printf("[%s]->[%d]任务发生中断.....%n",Thread.currentThread().getName(), a);
                            return;
                        }
                    }
                }
                System.out.printf("[%s]->[%d]任务执行完成.....%n",Thread.currentThread().getName(), a);
            },executorService);
            futures[i-1] = future;
        }

        CompletableFuture<Void> any = new CompletableFuture<>();
        CompletableFuture<Void> all = CompletableFuture.allOf(futures);
        Arrays.stream(futures).forEach(f->{
            f.exceptionally((e)->{
                //futures 其中一个任务发生异常
                any.completeExceptionally(e);
                return null;
            });
        });

        try {
            //任意任务执行完成
            CompletableFuture.anyOf(any,all).get();
        } catch (Exception e) {
            System.err.printf("[%s]->所有任务发生异常【%s-%s】.....%n",Thread.currentThread().getName(),e.getClass().getName(),e.getMessage());
            //当任务3发生异常，其实后面任务并未结束
            //executorService.shutdown();
            //线程池关闭，其他正在执行的线程会发生中断
            executorService.shutdownNow();
        }
        System.out.printf("[%s]->所有任务处理完成.....%n",Thread.currentThread().getName());
        try {
            TimeUnit.MINUTES.sleep(5L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
