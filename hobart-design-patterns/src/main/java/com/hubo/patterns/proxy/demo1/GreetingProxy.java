package com.hubo.patterns.proxy.demo1;

public class GreetingProxy implements Greeting{
    
    private Greeting greeting;
    
    public GreetingProxy(Greeting greeting) {
        this.greeting = greeting;
    }

    @Override
    public void sayHello(String name) {
        before();
        greeting.sayHello(name);
        after();
    }

    private void after() {
        System.out.println("After");
    }

    private void before() {
        System.out.println("Before");
    }
}
