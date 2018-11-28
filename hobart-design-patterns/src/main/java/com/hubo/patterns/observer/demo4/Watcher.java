package com.hubo.patterns.observer.demo4;

public class Watcher implements WatcherObserver {
    //职务
    private String job;
    
    @Override
    public void update(WaterQualitySubject subject) {
        System.out.println(job+"获取通知，当前污染级别为："+subject.getPolluteLevel());
    }

    @Override
    public void setJob(String job) {
        this.job=job;
    }

    @Override
    public String getJob() {
        return this.job;
    }
}
