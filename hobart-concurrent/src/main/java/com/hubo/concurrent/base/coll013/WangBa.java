package com.hubo.concurrent.base.coll013;

import java.util.concurrent.DelayQueue;

public class WangBa implements Runnable{
    
    private DelayQueue<WangMin> queue=new DelayQueue<>(); 
    
    public boolean yingye=true;
    
    public void shangji(String name,String id,int money){
        WangMin man=new WangMin(name,id,1000*money+System.currentTimeMillis());
        System.out.println("网名"+man.getName()+" 身份证"+man.getId()+"交钱"+money+"块，开始上机....");
        this.queue.add(man);
    }
    
    public void xiaji(WangMin man){
        System.out.println("网民"+man.getName()+" 身份证"+man.getId()+"时间到下机...");
    }
    
    @Override
    public void run() {
        while (yingye) {
            try {
                WangMin man = queue.take();
                xiaji(man);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("网吧开始营业");
        WangBa wb=new WangBa();
        Thread shangwang=new Thread(wb);
        shangwang.start();
        
        wb.shangji("张三", "123", 1);
        wb.shangji("李四", "234", 10);
        wb.shangji("王五", "345", 5);
    }
}
