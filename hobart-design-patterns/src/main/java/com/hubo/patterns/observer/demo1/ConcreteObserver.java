package com.hubo.patterns.observer.demo1;

public class ConcreteObserver implements Observer{
    private String subjectState;
    
    @Override
    public void update(Subject subject) {
        this.subjectState = ((ConcreteSubject)subject).getSubjectState();
        System.out.println(">>>>>"+subjectState);
    }
}
