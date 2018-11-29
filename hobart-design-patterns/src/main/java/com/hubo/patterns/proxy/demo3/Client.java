package com.hubo.patterns.proxy.demo3;

public class Client {

    public static void main(String[] args) {
        Greeting proxy = CGLibDynamicProxy.getInstance().getProxy(GreetingImp.class);
        
        proxy.sayHello("marry");
        
    }
    
    public static class GreetingImp extends Greeting{

        @Override
        public void sayHello(String name) {
            System.out.println("CGLib动态代理，"+name);
        }
    }
}
