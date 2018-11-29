package com.hubo.patterns.proxy.demo1;

public class GreetingImpl implements Greeting {
    

    @Override
    public void sayHello(String name) {
        before();
        System.out.println("Hello," + name);
        after();
    }

    private void after() {
        System.out.println("After");
    }

    private void before() {
        System.out.println("Before");
    }
}
