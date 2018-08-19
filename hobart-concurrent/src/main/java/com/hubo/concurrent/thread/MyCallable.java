package com.hubo.concurrent.thread;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<Integer>{
    
    
    @Override
    public Integer call() throws Exception {
        Thread.sleep(3000);
        return 1+1;
    }
}
