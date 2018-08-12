package com.hubo.patterns.singleton.github;

public class ThreadSafeDoubleCheckLockingTest extends AbstractSingletonTest<ThreadSafeDoubleCheckLocking>{
    
    public ThreadSafeDoubleCheckLockingTest(){
        super(ThreadSafeDoubleCheckLocking :: getInstance);
    }
}
