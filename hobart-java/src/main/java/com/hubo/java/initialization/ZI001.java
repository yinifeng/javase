package com.hubo.java.initialization;

public class ZI001 extends FU{
    public ZI001(){
        System.out.println("ZI001 Constructor....");
    }
    
    static {
        System.out.println("ZI001 static.....");
    }
    
    @Override
    protected void say() {
        System.out.println("ZI001 说英语....");
    }

    @Override
    public void doing() {
        System.out.println("ZI001 编程.....");
    }

    @Override
    public void like() {
        super.like();
        System.out.println("ZI001 打篮球....");
    }

    public static void main(String[] args) {
        ZI001 zi001=new ZI001();
        zi001.doing();
        zi001.like();
        zi001.say();
    }
}
