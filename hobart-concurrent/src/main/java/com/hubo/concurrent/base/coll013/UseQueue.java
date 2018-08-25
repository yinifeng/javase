package com.hubo.concurrent.base.coll013;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;

public class UseQueue {

    public static void main(String[] args) {
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
        
        
    }
}
