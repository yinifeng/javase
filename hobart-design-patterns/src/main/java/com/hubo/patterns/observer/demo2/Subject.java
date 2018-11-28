package com.hubo.patterns.observer.demo2;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    
    private List<Observer> readers=new ArrayList<>();
    
    public void attach(Observer reader){
        readers.add(reader);
    }
    
    public void detach(Observer reader){
        readers.remove(reader);
    }
    
    public void notifyReaders(){
        for (Observer reader:readers){
            reader.update(this);
        }
    }
}
