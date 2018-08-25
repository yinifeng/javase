package com.hubo.concurrent.base.conn011;

public class DubbleSingleton {
    
    private static volatile DubbleSingleton ds;
    
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
        Thread t1=new Thread(()-> System.out.println(DubbleSingleton.getInstance().hashCode()),"t1");
        Thread t2=new Thread(()->System.out.println(DubbleSingleton.getInstance().hashCode()),"t2");
        Thread t3=new Thread(()->System.out.println(DubbleSingleton.getInstance().hashCode()),"t3");
        
        t1.start();
        t2.start();
        t3.start();
    }
}
