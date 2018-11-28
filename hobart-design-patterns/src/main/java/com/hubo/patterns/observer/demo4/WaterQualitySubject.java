package com.hubo.patterns.observer.demo4;

import java.util.ArrayList;
import java.util.List;

public abstract class WaterQualitySubject {
    
    protected List<WatcherObserver> observers=new ArrayList<>();
    
    public void attach(WatcherObserver observer){
        observers.add(observer);
    }
    
    public void detach(WatcherObserver observer){
        observers.remove(observer);
    }
    
    public abstract void notifyObservers();
        
    public abstract int getPolluteLevel();
}
