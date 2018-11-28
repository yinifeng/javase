package com.hubo.patterns.observer.demo2;

public class Reader implements Observer{
    private String name;
    
    @Override
    public void update(Subject subject) {
        System.out.println(this.name+"收到报纸了，阅读内容是"+((NewsPaper)subject).getContent());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
