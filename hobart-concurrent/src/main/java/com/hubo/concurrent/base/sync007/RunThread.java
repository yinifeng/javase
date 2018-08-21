package com.hubo.concurrent.base.sync007;

public class RunThread extends Thread{
    private volatile boolean isRunning=true;
    private void setRunning(boolean isRunning){
        this.isRunning=isRunning;
    }

    @Override
    public void run() {
        System.out.println("进入run方法..."+Thread.currentThread().getName());
        int i=0;
        while (isRunning){
            
        }
        System.out.println("线程停止工作");
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("进入主线程："+Thread.currentThread().getName());
        RunThread rt=new RunThread();
        rt.start();
        Thread.sleep(1000);

        /**
         * 这个变量的主线程 设置成false
         * 若这个变量未加volatile修饰 
         *  另外的线程获取不到这个被主线程设置的值，那么就停止不了
         */
        rt.setRunning(false);
        System.out.println("isRunning的值已经被设置成false");
    }
}
