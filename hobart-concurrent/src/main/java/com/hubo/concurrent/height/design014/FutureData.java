package com.hubo.concurrent.height.design014;

public class FutureData implements Data {
    private RealData realData;
    
    private boolean isReady=false;
    
    public synchronized void setRealData(RealData realData){
        //如果装载完毕，直接返回
        if (isReady){
            return;
        }
        //如果没装载，进行装载真实对象
        this.realData=realData;
        isReady=true;
        //进行通知
        this.notify();
    }
    
    @Override
    public synchronized String getRequest() {
        //如果没装载好程序一直处于阻塞状态
        while (!isReady){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //装载好直接将数据返回即可
        return this.realData.getRequest();
    }
}
