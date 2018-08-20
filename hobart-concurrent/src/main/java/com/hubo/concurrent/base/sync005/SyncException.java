package com.hubo.concurrent.base.sync005;

/**
 * synchronized异常
 */
public class SyncException {
    private int i=0;
    public synchronized void operation(){
        for(;;){
            try {
                i++;
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName()+" ,i = "+i);
                if (i==20){
                    throw new RuntimeException();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        final SyncException se=new SyncException();
        Thread t1=new Thread(()->se.operation(),"t1");
        t1.start();
    }
}
