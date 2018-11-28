package com.hubo.java.innerclass;

public class OutClass {
    
    static {
        System.out.println("OutClass static init.....");
    }
    
    public OutClass(){
        System.out.println("OutClass Constructor...");
    }
    
    //非静态内部类
    public class InnerClass{
        //内部类不能有静态块
//        static {
//            System.out.println("InnerClass static....");
//        }
        //内部类不能有静态变量
//        private static int i=1;

        //内部类不能有静态方法
//        public static void say(){
//            System.out.println("Say Hello InnerClass");
//        }
        
        {
            System.out.println("InnerClass init....");
        }
        
        private int a=1;
        
        public InnerClass(){
            System.out.println("InnerClass Constructor...");
        }
    }
    
    //静态内部类
    public static class StaticInnerClass{
        static {
            System.out.println("StaticInnerClass static init....");
        }
        
        public static int b=1;
        
        {
            System.out.println("StaticInnerClass init....");
        }
        public StaticInnerClass(){
            System.out.println("StaticInnerClass Constructor...");
        }
    }
    
    
    enum InnerType{
        Inner1,Inner2;
    }
    
}
