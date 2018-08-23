package com.hubo.concurrent.base.conn008;

import java.util.ArrayList;
import java.util.List;

public class ListAdd1 {
    private volatile List<String> list = new ArrayList<>();

    public void add() {
        list.add("hobart");
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        final ListAdd1 la = new ListAdd1();
        Thread t1 = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    la.add();
                    System.out.println("当前线程：" + Thread.currentThread().getName() + "添加一个元素..");
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            for (; ; ) {
                if (la.size() == 5){
                    System.out.println("当前线程收到通知：" + Thread.currentThread().getName() + "list size = 5 线程停止..");
                    throw new RuntimeException();
                }
            }
        }, "t2");
        
        t1.start();
        t2.start();
    }
}
