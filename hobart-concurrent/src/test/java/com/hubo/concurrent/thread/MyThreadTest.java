package com.hubo.concurrent.thread;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.*;

public class MyThreadTest {

    private ExecutorService executorService;

    @Before
    public void init() {
        //Integer.MAX 线程池
        executorService = Executors.newCachedThreadPool();
    }

    @Test
    public void run1() throws Exception {
        System.out.println("==========start===========");
        List<Thread> tasks= new ArrayList<>();
        
        for (int i=0 ; i < 10 ;i++) {
            tasks.add(new MyThread("t"+i));
        }
        
        tasks.forEach((t) ->t.start());
        
        //主线程继续执行
        System.out.println("==========end===========");
    }

    @After
    public void destory() throws Exception {
        executorService.shutdown();
        System.in.read();
    }
}