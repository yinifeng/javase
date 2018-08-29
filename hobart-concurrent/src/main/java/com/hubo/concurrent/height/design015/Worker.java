package com.hubo.concurrent.height.design015;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Handler;

public class Worker implements Runnable{
    private ConcurrentLinkedQueue<Task> workQueue;
    
    private ConcurrentMap<String,Object> resultMap;

    public void setWorkQueue(ConcurrentLinkedQueue<Task> workQueue) {
        this.workQueue = workQueue;
    }

    public void setResultMap(ConcurrentMap<String, Object> resultMap) {
        this.resultMap = resultMap;
    }

    @Override
    public void run() {
        while (true) {
            Task input = this.workQueue.poll();
            if (input == null) {
                break;
            }
            Object output = handler(input);
            this.resultMap.put(Integer.toString(input.getId()), output);
        }
    }

    private Object handler(Task input) {
        Object output=null;
        //处理任务耗时
        try {
            Thread.sleep(500);
            output=input.getPrice();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return output;
    }
}
