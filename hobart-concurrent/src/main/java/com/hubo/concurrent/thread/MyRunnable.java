package com.hubo.concurrent.thread;

/**
 * 实现 Runnable
 */
public class MyRunnable implements Runnable{
    
    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" Runnable run....");
    }
}
