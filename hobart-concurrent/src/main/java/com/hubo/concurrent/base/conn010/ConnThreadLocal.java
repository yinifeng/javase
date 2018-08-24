package com.hubo.concurrent.base.conn010;

import java.util.concurrent.TimeUnit;


public class ConnThreadLocal {
    private static ThreadLocal<String> th=new ThreadLocal<>();
    
    public void setTh(String value){
        th.set(value);
    }
    
    public void getTh(){
        System.out.println(Thread.currentThread().getName()+":"+this.th.get());
    }

    public static void main(String[] args) {
        final ConnThreadLocal ct=new ConnThreadLocal();
        
        Thread t1=new Thread(()->{
            ct.setTh("张三");
            ct.getTh();
        },"t1");
        
        
        Thread t2=new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
                ct.getTh();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t2");
        
        t1.start();
        t2.start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        ct.getTh();
    }
    
}
