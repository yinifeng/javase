package com.hubo.concurrent.base.conn011;

/**
 * 单例
 */
public class Singleton {
    
    private static class InnerSingleton{
        private static final Singleton SINGLETON=new Singleton();
    }
    
    public static Singleton getInstance(){
        return InnerSingleton.SINGLETON;
    }
}
