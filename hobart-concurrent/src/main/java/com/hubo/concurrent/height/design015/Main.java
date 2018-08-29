package com.hubo.concurrent.height.design015;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        System.out.println("我的机器可用Processor数量:" + Runtime.getRuntime().availableProcessors());
        Master master=new Master(new Worker(),20);

        Random r=new Random();
        for (int i=1;i<=100;i++){
            Task t=new Task();
            t.setId(i);
            t.setPrice(r.nextInt(1000));
            master.submit(t);
        }
        master.execute();
        long start=System.currentTimeMillis();
        while (true){
            if (master.isComplete()){
                long end=System.currentTimeMillis();
                int priceResult=master.getResult();
                System.out.println("最终结果："+priceResult+",执行时间："+end);
                break;
            }
        }
        
    }
}
