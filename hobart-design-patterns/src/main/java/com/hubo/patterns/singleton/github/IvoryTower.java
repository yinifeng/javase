package com.hubo.patterns.singleton.github;

/**
 * 饿汉式
 */
public final class IvoryTower {
    private static final IvoryTower INSTANCE=new IvoryTower();
    
    private IvoryTower(){}
    
    public static IvoryTower getInstance(){
        return INSTANCE;
    }
    
}
