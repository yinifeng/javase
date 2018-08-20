package com.hubo.concurrent.base.sync006;

/**
 * synchronized代码块对字符串的锁，注意String常量池的缓存功能
 */
public class StringLock {
    public void method(){
        synchronized ("字符串常量"){
            try {
                for (;;){
                    System.out.println("当前线程 ："+Thread.currentThread().getName()+"开始");
                    Thread.sleep(1000);
                    System.out.println("当前线程 ："+Thread.currentThread().getName()+"结束");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * t1 和 t2 谁抢到cpu的执行权 谁就持有这把锁
     * @param args
     */
    public static void main(String[] args) {
        final StringLock sl=new StringLock();
        Thread t1=new Thread(()->sl.method(),"t1");
        Thread t2=new Thread(()->sl.method(),"t2");
        t1.start();
        t2.start();
    }
}
