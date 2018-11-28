package com.hubo.patterns.observer.demo4;

public class WaterQuality extends WaterQualitySubject {
    
    /**污染级别**/
    private int polluteLevel=0;

    public void setPolluteLevel(int polluteLevel) {
        this.polluteLevel = polluteLevel;
        this.notifyObservers();
    }


    /**通知所有观察者对象
     * 
     */
    @Override
    public void notifyObservers() {
        for (WatcherObserver water:this.observers){
            if (this.polluteLevel >= 0){
                if("监测人员".equals(water.getJob())){
                    water.update(this);
                }
            }
            
            if(this.polluteLevel >= 1){
                if("预警人员".equals(water.getJob())){
                    water.update(this);
                }
            }
            
            if(this.polluteLevel >= 2){
                if("监测部门领导".equals(water.getJob())){
                    water.update(this);
                }
            }
            
            
        }
    }

    @Override
    public int getPolluteLevel() {
        return this.polluteLevel;
    }
}
