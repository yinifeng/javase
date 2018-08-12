package com.hubo.patterns.singleton.github;

/**
 * 线程安全的懒汉式
 */
public class ThreadSafeLazyLoadedIvoryTower {
    private static ThreadSafeLazyLoadedIvoryTower instance;
    
    private ThreadSafeLazyLoadedIvoryTower(){
        if (instance == null) {
            instance = this;
        }else {
            throw new IllegalStateException("Already initialized.");
        }
    }

    /**
     * Lazy-loading
     * @return
     */
    public static synchronized ThreadSafeLazyLoadedIvoryTower getInstance(){
        if (instance == null) {
            instance = new ThreadSafeLazyLoadedIvoryTower();
        }
        return instance;
    }
}
