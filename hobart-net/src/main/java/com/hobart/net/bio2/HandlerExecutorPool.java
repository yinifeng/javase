package com.hobart.net.bio2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class HandlerExecutorPool {

    private final ExecutorService pool;

    public HandlerExecutorPool(int maxPoolSize, int queueSize) {
        this.pool = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
                maxPoolSize,
                120L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(queueSize));
    }
    
    public void execute(Runnable task){
        this.pool.execute(task);
    }
}
