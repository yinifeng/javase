package com.hubo.concurrent.base.coll013;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

public class UseQueue {

    public static void main(String[] args) throws InterruptedException {
        //高性能无阻塞无界队列：ConcurrentLinkedQueue

        ConcurrentLinkedQueue<String> queue1=new ConcurrentLinkedQueue<>();
        queue1.offer("a");
        queue1.offer("b");
        queue1.offer("c");
        queue1.offer("d");
        queue1.add("e");
        System.out.println("queue1=>"+queue1);
        System.out.println(queue1.poll()); //a 从头部取出元素，并从队列里删除
        System.out.println(queue1.size()); //4
        System.out.println(queue1.peek()); //b
        System.out.println(queue1.size()); //4

        System.out.println("------------------------------");
        final ArrayBlockingQueue<String> queue2=new ArrayBlockingQueue<String>(5);
        queue2.put("a");
        queue2.put("b");
        queue2.add("c");
        queue2.add("d");
        queue2.add("e");
        //queue2.add("f");// put超过长度阻塞
        System.out.println("queue2==>"+queue2);
        System.out.println(queue2.offer("a",3, TimeUnit.SECONDS));
        System.out.println(queue2.poll(3, TimeUnit.SECONDS));
        System.out.println("queue2==>"+queue2);
        System.out.println("--------------------------------");
        
        //阻塞队列
        LinkedBlockingQueue<String> queue3=new LinkedBlockingQueue<>();
        queue3.offer("a");
        queue3.offer("b");
        queue3.offer("c");
        queue3.offer("d");
        queue3.offer("e");
        queue3.add("f");
        System.out.println(queue3.size());
        
//        for (Iterator<String> it = queue3.iterator(); it.hasNext();){
//            String value = it.next();
//            System.out.println(value);
//        }

        List<String> list=new ArrayList<>();
        System.out.println(queue3.drainTo(list,3));
        System.out.println("queue3==>"+queue3);
        System.out.println(list.size());
        System.out.println("list==>"+list);

        System.out.println("-----------------------");
        final SynchronousQueue<String> queue4=new SynchronousQueue<>();
        Thread t1=new Thread(()->{
            try {
                System.out.println(queue4.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        
        Thread t2=new Thread(()->{
            try {
                for (int i=0;i<10;i++){ 
                    //queue4.add("adsdfsd");
                    queue4.put("adsfs");//阻塞
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t2.start();
        
    }
}

