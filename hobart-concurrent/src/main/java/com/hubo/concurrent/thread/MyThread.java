package com.hubo.concurrent.thread;

/**
 * 继承 Thread
 */
public class MyThread extends Thread{
    
    public MyThread(){
        
    }
    
    public MyThread(String name){
        super(name);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" run....");
    }
}
