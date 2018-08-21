package com.hubo.concurrent.base.sync007;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile关键字不具备synchronized关键字的原子性（同步）
 */
public class VolatileNotAtomic extends Thread{
    private volatile static int count;
    //private static AtomicInteger count=new AtomicInteger(0);
    
    private static void addCount(){
        for (int i=0;i<1000;i++){
            count++;
            //count.incrementAndGet();
        }
        System.out.println(count);
    }

    @Override
    public void run() {
        addCount();
    }

    public static void main(String[] args) {
        VolatileNotAtomic[] arr=new VolatileNotAtomic[100];
        for (int i=0;i<100;i++){
            arr[i]=new VolatileNotAtomic();
        }
        
        for (VolatileNotAtomic vna:arr){
            vna.start();
        }
    }
}
