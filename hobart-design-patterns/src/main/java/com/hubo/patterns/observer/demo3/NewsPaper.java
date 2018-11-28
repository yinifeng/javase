package com.hubo.patterns.observer.demo3;

import java.util.Observable;

public class NewsPaper extends Observable{
    
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        
        this.setChanged();
        //推模式
        this.notifyObservers(content);
        //拉模式
        //this.notifyObservers();
    }
}
