package com.hubo.patterns.observer.demo3;

import java.util.Observable;
import java.util.Observer;

public class Reader implements Observer{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        //同时支持推模式和拉模式

        System.out.println(name+"收到报纸了，阅读先。目标推过来的内容是==="+arg);
        System.out.println(name+"收到报纸了，阅读先。主动到目标对象去拉的内容是==="+((NewsPaper) o).getContent());
        
    }
}
