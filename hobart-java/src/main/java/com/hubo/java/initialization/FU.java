package com.hubo.java.initialization;

/**
 * 抽象类实现接口，可以不实现方法
 */
public abstract class FU implements YE{

    public FU() {
        System.out.println("FU Constructor.....");
    }
    
    static {
        System.out.println("FU static......");
    }
    
    {
        System.out.println("FU 程序块....");
    }
    
    protected abstract void say();
    
    public void like(){
        System.out.println("Fu 下棋....");
    }
}
