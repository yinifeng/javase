package com.hobart.data.stack;

import java.util.Scanner;

/**
 * 栈 数组实现
 * 
 * 
 * 单链表也可以模拟实现
 */
public class ArrayStackDemo {

    public static void main(String[] args) {
        //测试数组实现的栈
        ArrayStack stack=new ArrayStack(4);
        String key="";
        boolean loop=true;
        Scanner scanner=new Scanner(System.in);
        while (loop){
            System.out.println("show:显示栈的元素");
            System.out.println("exit:程序退出");
            System.out.println("pop:从栈中取出元素(出栈)");
            System.out.println("push:往栈中添加元素(压栈)");
            System.out.println("请选择一种操作");
            key=scanner.next();
            switch (key){
                case "show":
                    stack.show();
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                case "pop":
                    try {
                        System.out.println(stack.pop());
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case "push":
                    System.out.println("请输入一个整数");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出~~");
    }

    //数组模拟栈
    private static class ArrayStack {
        private int maxSize;//栈的大小
        private int[] array;
        private int top = -1;

        public ArrayStack(int maxSize) {
            this.maxSize = maxSize;
            array = new int[this.maxSize];
        }

        //栈是否满
        public boolean isFull() {
            return top == maxSize - 1;
        }

        //栈是否为空
        public boolean isEmpty() {
            return top == -1;
        }

        //压栈
        public void push(int value) {
            if (isFull()) {
                System.err.println("栈已满,不能压入数据");
                return;
            }
            top++;
            array[top] = value;
        }

        //出栈
        public int pop() {
            if (isEmpty()) {
                throw new RuntimeException("栈为空,不能出栈");
            }
            int value = array[top];
            top--;
            return value;
        }

        //遍历栈，从栈顶往栈底遍历
        public void show() {
            if (isEmpty()) {
                System.err.println("栈为空,没有数据");
                return;
            }
            for (int i = top; i >= 0; i--) {
                System.out.printf("stack[%d]=%d\n", i, array[i]);
            }
        }
    }
}
