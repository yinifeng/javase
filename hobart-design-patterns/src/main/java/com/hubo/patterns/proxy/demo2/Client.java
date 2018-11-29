package com.hubo.patterns.proxy.demo2;

/**
 * jdk 动态代理
 */
public class Client {
    public static void main(String[] args) {
        Greeting proxy = new JDKDynamicProxy(new Greeting() {
            @Override
            public void sayHello(String name) {
                System.out.println("JDK动态代理," + name);
            }
        }).getProxy();
        
        proxy.sayHello("tom");
        System.out.println("------------------------------");
        System.out.println(proxy);
    }
}
