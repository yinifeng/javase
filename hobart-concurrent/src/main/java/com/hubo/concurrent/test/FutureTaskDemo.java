package com.hubo.concurrent.test;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;

public class FutureTaskDemo {
    public static void main(String[] args) throws Exception {
        ExecutorService service = Executors.newFixedThreadPool(10);

        List<Future<String>> futures = new ArrayList<>(3);

        long start = System.currentTimeMillis();
        for (int i = 3; i >= 1; i--) {
            Future<String> f = service.submit(new MyCallable(i));
            futures.add(f);
        }

        for (Future<String> future : futures) {
            System.out.printf("任务【%s】执行完成.\n",future.get());
        }

        System.out.println("执行总耗时：" + (System.currentTimeMillis() - start) /1000);
        TimeUnit.HOURS.sleep(1L);
        service.shutdown();
    }

    private static class MyCallable implements Callable<String>{

        private final int index;

        public MyCallable(int index) {
            this.index = index;
        }

        @Override
        public String call() throws Exception {
            long start = System.currentTimeMillis();
            TimeUnit.SECONDS.sleep(index * 2L);
            String str = UUID.randomUUID().toString();
            System.out.println(String.format("[线程=%s]:[%s]任务%d执行耗时：",Thread.currentThread().getName(),str,index) + (System.currentTimeMillis() - start) /1000);
            return str;
        }

        public int getIndex() {
            return index;
        }
    }
}
