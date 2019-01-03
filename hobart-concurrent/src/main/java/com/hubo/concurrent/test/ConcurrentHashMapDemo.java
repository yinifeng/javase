package com.hubo.concurrent.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ConcurrentHashMap 测试 
 */
public class ConcurrentHashMapDemo {
    
    static ConcurrentHashMap<String,List<String>> cache=new ConcurrentHashMap<>();

    public static void main(String[] args) {

        //putData();

        putIfAbsentData();
    }

    private static void putIfAbsentData() {

        Thread one=new Thread(()->{
            List<String> list=new ArrayList<>();
            list.add("device1");
            list.add("device2");
            List<String> list1 = cache.putIfAbsent("t1", list);
            if (list1 != null){
                list1.addAll(list);
            }

            System.out.println(cache);
        });

        Thread two=new Thread(()->{
            List<String> list=new ArrayList<>();
            list.add("device11");
            list.add("device22");
            List<String> list1 = cache.putIfAbsent("t2", list);
            if (list1 != null){
                list1.addAll(list);
            }

            System.out.println(cache);
        });

        Thread three=new Thread(()->{
            List<String> list=new ArrayList<>();
            list.add("device111");
            list.add("device222");
            List<String> list1 = cache.putIfAbsent("t1", list);
            if (list1 != null){
                list1.addAll(list);
            }

            System.out.println(cache);
        });

        one.start();
        two.start();
        three.start();
    }
    
    

    private static void putData() {
        
        Thread one=new Thread(()->{
            List<String> list=new ArrayList<>();
            list.add("device1");
            list.add("device2");
            cache.put("t1", list);

            System.out.println(cache);
        });

        Thread two=new Thread(()->{
            List<String> list=new ArrayList<>();
            list.add("device11");
            list.add("device22");
            cache.put("t2", list);

            System.out.println(cache);
        });

        Thread three=new Thread(()->{
            List<String> list=new ArrayList<>();
            list.add("device111");
            list.add("device222");
            cache.put("t1", list);

            System.out.println(cache);
        });
        
        one.start();
        two.start();
        three.start();
    }


}
