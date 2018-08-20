package com.hubo.concurrent.base.sync006;

/**
 * 使用synchronized代码块加锁,比较灵活
 */
public class ObjectLock {
    
    public void method1(){
        synchronized (this){ //对象锁
            try {
                System.out.println("do method1...");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void method2(){
        synchronized (ObjectLock.class) {//类锁
            try {
                System.out.println("do method2...");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    private Object obj=new Object();
    
    public void method3(){
        synchronized (obj){//任何对象锁
            try {
                System.out.println("do method3...");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        final ObjectLock ol=new ObjectLock();
        Thread t1=new Thread(()->ol.method1());
        Thread t2=new Thread(()->ol.method2());
        Thread t3=new Thread(()->ol.method3());
        
        t1.start();
        t2.start();
        t3.start();
    }
}
