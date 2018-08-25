package com.hubo.concurrent.base.coll013;


import java.util.concurrent.PriorityBlockingQueue;

public class UsePriorityBlockingQueue {
    
    public static class Task implements Comparable<Task>{
        private int id;
        
        private String name;

        public Task() {
            
        }

        public Task(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public int compareTo(Task o) {
            return this.id > o.getId() ? 1 : (this.id < o.getId() ? -1 : 0);
        }

        @Override
        public String toString() {
            return this.id+"="+this.name;
        }
    }
    
    
    public static void main(String[] args) throws InterruptedException {
        PriorityBlockingQueue<Task> queue=new PriorityBlockingQueue<>();
        
        Task t1=new Task(3,"id为3");
        Task t2=new Task(4,"id为4");
        Task t3=new Task(1,"id为1");
        
        queue.add(t1);
        queue.add(t2);
        queue.add(t3);

        System.out.println("容器："+queue);
        System.out.println(queue.take().getId());
        System.out.println("容器："+queue);
    }
}
