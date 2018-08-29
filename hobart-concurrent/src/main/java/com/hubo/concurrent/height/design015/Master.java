package com.hubo.concurrent.height.design015;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;

public class Master {
    //1、有一个盛放任务的容器
    private ConcurrentLinkedQueue<Task> workQueue=new ConcurrentLinkedQueue<>();
    
    //2、需要一个盛放worker的集合
    private Map<String,Thread> workers=new HashMap<>();
    
    //3、需要一个盛放每一个worker执行任务的结果集合
    private ConcurrentMap<String,Object> resultMap=new ConcurrentHashMap<>();
    
    //4、构造方法
    public Master(Worker worker,int workerCount){
        worker.setWorkQueue(this.workQueue);
        worker.setResultMap(this.resultMap);
        
        for (int i=0;i<workerCount;i++){
            this.workers.put(Integer.toString(i), new Thread(worker));
        }
    }
    
    //5、需要提交任务的方法
    public void submit(Task task){
        this.workQueue.add(task);
    }
    
    //6.需要一个执行的方法。启动所有的worker方法去执行任务
    public void execute(){
        for (Map.Entry<String,Thread> entry:workers.entrySet()){
            entry.getValue().start();
        }
    }
    
    //7、判断是否运行结束的方法
    public boolean isComplete(){
        for (Map.Entry<String,Thread> entry:workers.entrySet()){
            if (entry.getValue().getState() != Thread.State.TERMINATED){
                return false;
            }
        }
        return true;
    }
    
    //8.计算结果方法
    public int getResult(){
        int priceResult=0;
        for (Map.Entry<String,Object> entry:resultMap.entrySet()){
            priceResult+=(Integer)entry.getValue();
        }
        return priceResult;
    }
}
