package com.hubo.patterns.observer.demo4;

public interface WatcherObserver {
    
    void update(WaterQualitySubject subject);
    
    void setJob(String job);
    
    String getJob();
}
