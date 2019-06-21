package com.hobart.data.queue;

import java.util.Scanner;

/**
 * 数组模拟Queue
 * 
 * 数组使用一次不能用了
 * 代码存在问题，当队列满时没法再加入数据，即使出队列
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue arrayQueue=new ArrayQueue(3);
        char key = ' ';//接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出菜单
        while (loop){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列头的数据");
            key = scanner.next().charAt(0);//接收一个字符 
            switch (key){
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    System.out.println("程序退出~~");
                    break;
                case 'a':
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g':
                    try {
                        System.out.printf("取出的数据是%d\n",arrayQueue.getQueue());
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        System.out.printf("队列头的数据是%d\n",arrayQueue.headQueue());
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                default:
                    break;
            }
            
        }
        
    }

    //数组模拟一个队列
    private static class ArrayQueue{
        private int maxSize;//数组最大的容量

        private int front;//队列头

        private int rear;//队列尾

        private int[] arr;//该数组用于存放数据，模拟队列

        public ArrayQueue(int size){
            this.maxSize = size;
            this.front = -1;
            this.rear = -1;
            this.arr = new int[size];
        }
        
        public boolean isFull(){
            return this.rear == this.maxSize - 1;
        }
        
        public boolean isEmpty(){
            return this.rear == this.front;
        }
        
        //向队列中添加数据
        public void addQueue(int ele){
            if (this.isFull()) {
                System.err.println("队列已满,不能加入数据..");
                return;
            }
            //this.rear++;
            this.arr[++this.rear] = ele;
        }
        
        //从队列中获取数据,出队列
        public int getQueue(){
            if (this.isEmpty()){
                throw new RuntimeException("队列为空,不能获取数据");
            }
            this.front++;
            return this.arr[this.front];
        }
        
        //显示队列数据
        public void showQueue(){
            //遍历
            if (isEmpty()){
                System.err.println("队列为空,不能获取数据~~");
                return;
            }
            for (int i = 0; i < arr.length; i++){
                System.out.printf("arr[%d]=%d\n",i,arr[i]);
            }
        }
        
        //显示队列的头数据
        public int headQueue(){
            if (this.isEmpty()){
                throw new RuntimeException("队列为空,不能获取数据~~");
            }
            return this.arr[this.front+1];
        }
    }
}


