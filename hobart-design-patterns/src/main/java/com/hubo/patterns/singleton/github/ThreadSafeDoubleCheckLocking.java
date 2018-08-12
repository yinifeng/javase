package com.hubo.patterns.singleton.github;

/**
 * 双重检查锁
 */
public final class ThreadSafeDoubleCheckLocking {
    
    private volatile static ThreadSafeDoubleCheckLocking instance;
    
    private ThreadSafeDoubleCheckLocking(){
        if (instance !=null){
            throw new IllegalStateException("Already initialized.");
        }
    }
    
    public static ThreadSafeDoubleCheckLocking getInstance(){
        ThreadSafeDoubleCheckLocking result=instance;
        
        if (result == null) {
            synchronized (ThreadSafeDoubleCheckLocking.class) {
                result = instance;
                if (result == null) {
                    instance = result = new ThreadSafeDoubleCheckLocking();
                }
            }
        }
        
        return result;
    }
}
