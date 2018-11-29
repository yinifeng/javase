package com.hubo.patterns.proxy.demo3;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGLib 动态代理
 */
public class CGLibDynamicProxy implements MethodInterceptor{
    
    private static final CGLibDynamicProxy INSTANCE=new CGLibDynamicProxy();

    public CGLibDynamicProxy() {
    }
    
    public static CGLibDynamicProxy getInstance(){
        return INSTANCE;
    }
    
    public <T> T getProxy(Class<T> cls){
        return (T) Enhancer.create(cls, this);
    }
    
    @Override
    public Object intercept(Object target, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        before();
        Object result = methodProxy.invokeSuper(target, args);
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
