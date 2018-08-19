package com.hubo.concurrent.thread;

import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyRunnableTest {

    @Test
    public void testRunnable() {
        System.out.println("============start============");
        
        for (int i = 0; i < 5; i++) {
            new Thread(new MyRunnable(), "r" + i).start();
        }
        
        System.out.println("============end============");
    }

    @After
    public void destroy() throws Exception {
        System.in.read();
    }
}