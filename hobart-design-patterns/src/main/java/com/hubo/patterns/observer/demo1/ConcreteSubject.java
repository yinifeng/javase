package com.hubo.patterns.observer.demo1;

public class ConcreteSubject extends Subject {
    private String subjectState;

    public String getSubjectState() {
        return subjectState;
    }

    public void setSubjectState(String subjectState) {
        this.subjectState = subjectState;
        
        this.notifyObservers();
    }
}
