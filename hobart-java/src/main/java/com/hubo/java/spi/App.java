package com.hubo.java.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * spi 机制加载类
 * 
 * 接口
 * 抽象类都可以
 * 
 * 子类都可以
 */
public class App {
    public static void main(String[] args) {
        ServiceLoader<HelloSPI> spis = ServiceLoader.load(HelloSPI.class);

        Iterator<HelloSPI> its = spis.iterator();
        while (its.hasNext()){
            HelloSPI helloSPI = its.next();
            System.out.println(helloSPI);
            helloSPI.println();
            System.out.println("--------------");
        }
        
    }
}
