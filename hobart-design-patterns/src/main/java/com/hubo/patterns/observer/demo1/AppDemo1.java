package com.hubo.patterns.observer.demo1;


/**
 * 观察者模式
 */
public class AppDemo1 {

    public static void main(String[] args) {
        ConcreteSubject concreteSubject=new ConcreteSubject();
        ConcreteObserver observer1 = new ConcreteObserver();
        ConcreteObserver observer2 = new ConcreteObserver();
        concreteSubject.attach(observer1);
        concreteSubject.attach(observer2);
        
        concreteSubject.setSubjectState("helllo,observer");

        System.out.println("--------------------");
        concreteSubject.detach(observer1);
        concreteSubject.setSubjectState("aaaaaaaaaaaaaa");
        
        
    }
}
