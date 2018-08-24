package com.hubo.concurrent.base.conn011;

public class DubbleSingleton {
    
    private static DubbleSingleton ds;
    
    public static DubbleSingleton getInstance(){
        if (ds == null){
            try {
                //模拟初始化对象
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (DubbleSingleton.class){
                if (ds == null){
                    ds =new DubbleSingleton();
                }
            }
            
        }
        
        return ds;
    }

    public static void main(String[] args) {
        
    }
}
