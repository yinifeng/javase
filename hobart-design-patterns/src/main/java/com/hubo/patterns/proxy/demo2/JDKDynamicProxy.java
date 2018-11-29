package com.hubo.patterns.proxy.demo2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKDynamicProxy implements InvocationHandler{

    private Object target;
    
    public JDKDynamicProxy(Object target) {
        this.target = target;
    }
    
    public <T> T getProxy(){
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(),
            target.getClass().getInterfaces(),
                this
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        System.out.println(method.getName());
        Object result = method.invoke(target, args);
        after();
        return result;
    }

    private void after() {
        System.out.println("After");
    }

    private void before() {
        System.out.println("Before");
    }
}
