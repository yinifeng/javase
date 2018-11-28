package com.hubo.patterns.observer.demo1;

import java.util.ArrayList;
import java.util.List;

/**
 * 目标对象，它知道观察它的观察者，并提供注册和删除观察者借口
 */
public class Subject {
    
    private List<Observer> observers=new ArrayList<>();
    
    public void attach(Observer observer){
        observers.add(observer);
    }
    
    public void detach(Observer observer){
        observers.remove(observer);
    }
    
    protected void notifyObservers(){
        for (Observer observer:observers){
            observer.update(this);
        }
    }
}
