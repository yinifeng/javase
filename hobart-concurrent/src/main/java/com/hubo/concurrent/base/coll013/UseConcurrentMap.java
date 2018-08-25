package com.hubo.concurrent.base.coll013;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class UseConcurrentMap {

    public static void main(String[] args) {
        ConcurrentMap<String,Object> chm=new ConcurrentHashMap<>();
        chm.put("k1", "v1");
        chm.put("k2", "v2");
        chm.put("k3", "v3");
        chm.putIfAbsent("k4", "vvvvv");

        System.out.println(chm.get("k2"));
        System.out.println(chm.size());
        
        for (Map.Entry<String,Object> entry:chm.entrySet()){
            System.out.println("key:"+entry.getKey()+",value:"+entry.getValue());
        }
    }
}
