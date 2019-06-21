package com.hobart.data.queue;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.Scanner;

/**
 * 环形的数组队列
 * 
 * 思路如下:
 * 1.  front 变量的含义做一个调整： front 就指向队列的第一个元素, 也就是说 arr[front] 就是队列的第一个元素 
 * front 的初始值 = 0
 * 2.  rear 变量的含义做一个调整：rear 指向队列的最后一个元素的后一个位置. 因为希望空出一个空间做为约定.
 * rear 的初始值 = 0
 * 3. 当队列满时，条件是  (rear  + 1) % maxSize == front 【满】
 * 4. 对队列为空的条件， rear == front 空
 * 5. 当我们这样分析， 队列中有效的数据的个数   (rear + maxSize - front) % maxSize   // rear = 1 front = 0 
 * 6. 我们就可以在原来的队列上修改得到，一个环形队列
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        System.out.println(10 % 6);
        
        //设置队列长度为4 其实最大值为3
        CircleArrayQueue circleArrayQueue=new CircleArrayQueue(4);
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
                    circleArrayQueue.showQueue();
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    System.out.println("程序退出~~");
                    break;
                case 'a':
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    circleArrayQueue.addQueue(value);
                    break;
                case 'g':
                    try {
                        System.out.printf("取出的数据是%d\n",circleArrayQueue.getQueue());
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        System.out.printf("队列头的数据是%d\n",circleArrayQueue.headQueue());
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                default:
                    break;
            }

        }
    }
    
    //环形队列
    private static class CircleArrayQueue{
        private int maxSize;//数组最大的容量
        //初始值为0
        private int front;//队列头

        private int rear;//队列尾

        private int[] arr;//该数组用于存放数据，模拟队列
        
        public CircleArrayQueue(int size){
            this.maxSize = size;
            this.arr = new int[size];
        }
        
        //是否满
        public boolean isFull(){
            return (rear + 1) % maxSize == front;
        }
        
        //是否空
        public boolean isEmpty(){
            return rear == front;
        }
        
        //添加数据
        public void addQueue(int ele) {
            if (this.isFull()) {
                System.err.println("队列已满,不能加入数据..");
                return;
            }
            //this.rear++;
            this.arr[rear] = ele;
            //考虑取模
            this.rear = (this.rear + 1) % this.maxSize;
        }

        //从队列中获取数据,出队列
        public int getQueue(){
            if (this.isEmpty()){
                throw new RuntimeException("队列为空,不能获取数据");
            }
            //这里是要分析 front是指向队列的第一个元素
            //1.先把 front 对应的值保留到一个临时变量
            //2.将front 后移，考虑取模
            //3.将临时的变量返回
            int value = this.arr[front];
            this.front = (this.front + 1) % maxSize;
            return value;
        }

        //显示队列数据
        public void showQueue(){
            //遍历
            if (isEmpty()){
                System.err.println("队列为空,不能获取数据~~");
                return;
            }
            //从front开始遍历 ，遍历多少个元素
            for (int i = this.front; i < this.front + this.size(); i++){
                System.out.printf("arr[%d]=%d\n",i % maxSize,arr[i % maxSize]);
            }
        }
        
        //求出当前队列有效的个数
        public int size(){
            //rear = 2
            //front = 1
            //maxSize = 3
            return (this.rear + this.maxSize - this.front) % this.maxSize;
        }

        //显示队列的头数据
        public int headQueue(){
            if (this.isEmpty()){
                throw new RuntimeException("队列为空,不能获取数据~~");
            }
            return this.arr[this.front];
        }
    }
}
