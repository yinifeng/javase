package com.hubo.patterns.proxy.demo1;

/**
 * 代码写死 aop
 * 
 * 静态代理
 * 
 */
public class Client {
    public static void main(String[] args) {
        Greeting greeting=new GreetingImpl();
        greeting.sayHello("张三");

        System.out.println("---------------");
        GreetingProxy proxy=new GreetingProxy(new Greeting() {
            @Override
            public void sayHello(String name) {
                System.out.println("静态代理，"+name);
            }
        });
        proxy.sayHello("李四");
    }
}
