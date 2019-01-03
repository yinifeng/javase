package com.hubo.concurrent.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

public class WaitAllThread {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<String> list=new ArrayList<>();
        
        list.add("a");
        list.add("b");

        Iterator<String> it = list.iterator();

        String str = it.next();
        while (str !=null) {
            System.out.println(str);
            if(it.hasNext()){
                str=it.next();
            }else{
                break;
            }
        }

        System.out.println("======================================");
        ExecutorService service= Executors.newCachedThreadPool();

        Future<String> f1 = service.submit(new Task(1, 6000));
        Future<String> f2 = service.submit(new Task(2, 9000));
        
        List<Future<String>> futureList=new ArrayList<>();
        futureList.add(f1);
        futureList.add(f2);

        Iterator<Future<String>> fit = futureList.iterator();
        Future<String> ff = fit.next();
        while(true){
            if (ff.isDone()){
                System.out.println("$$$$$$$");
                System.out.println(ff.get());
                if(fit.hasNext()){
                    ff=fit.next();
                }else{
                    break;
                }
            }
        }

        System.out.println("################");
        service.shutdown();
    }
    
    
    
    public static class Task implements Callable<String>{
        private final int i;
        
        private final long time;
        
        public Task(int i,long time){
            this.i=i;
            this.time=time;
        }

        @Override
        public String call() throws Exception {
            Thread.sleep(time);
            return "sucess"+i;
        }
    }
}
