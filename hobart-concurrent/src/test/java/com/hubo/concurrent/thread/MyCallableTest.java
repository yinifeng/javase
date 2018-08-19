package com.hubo.concurrent.thread;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.Assert.*;

public class MyCallableTest {
    
    private ExecutorService pool;

    @Before
    public void setUp() throws Exception {
        this.pool= Executors.newSingleThreadExecutor();
    }

    @After
    public void tearDown() throws Exception {
       this.pool.shutdown();
       System.in.read();
    }

    @Test
    public void testCall() throws Exception {
        Future<Integer> result = pool.submit(new MyCallable());
        System.out.println("---->"+result.get());
    }
}