package com.hubo.concurrent.base.conn008;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * wait notfiy 方法，wait释放锁，notfiy不释放锁
 */
public class ListAdd2 {
    private volatile List<String> list = new ArrayList<>();

    public void add() {
        list.add("hobart");
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        final ListAdd2 la = new ListAdd2();

        // 1 实例化出来一个 lock
        // 当使用wait 和 notify 的时候 ， 一定要配合着synchronized关键字去使用
       // final Object lock=new Object();
        
        final CountDownLatch countDownLatch=new CountDownLatch(1);
        
        Thread t1 = new Thread(() -> {
            try {
               // synchronized (lock){
                    for (int i = 0; i < 10; i++) {
                        la.add();
                        System.out.println("当前线程：" + Thread.currentThread().getName() + "添加一个元素..");
                        Thread.sleep(500);
                        if (la.size() == 5){
                            System.out.println("已经发出通知..");
                            //lock.notify();
                            countDownLatch.countDown();
                        }
                    }
                //}
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            //synchronized (lock) {
                if (la.size() != 5){
                    try {
                        System.out.println("t2进入..");
                        //lock.wait();
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("当前线程收到通知：" + Thread.currentThread().getName() + " list size = 5 线程停止..");
                throw new RuntimeException();
                
           // }
        }, "t2");
        
        t1.start();
        t2.start();
    }
}
