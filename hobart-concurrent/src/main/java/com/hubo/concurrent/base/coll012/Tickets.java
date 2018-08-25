package com.hubo.concurrent.base.coll012;

import java.util.Vector;

/**
 * 多线程使用Vector或者HashTable的示例（简单线程同步问题）
 * @author hubo
 */
public class Tickets {

    public static void main(String[] args) {
        final Vector<String> tickets=new Vector<>();
        
        for (int i=1;i<=1000;i++){
            tickets.add("火车票"+i);
        }
        
        for (int i=1;i<=10;i++){
            new Thread("线程"+i){
                @Override
                public void run() {
                    while (true){
                        if (tickets.isEmpty()) break;
                        System.out.println(Thread.currentThread().getName()+"-----"+tickets.remove(0));
                    }
                }
            }.start();
        }
    }
}
