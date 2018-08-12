package com.hubo.patterns.singleton.github;

import com.sun.xml.internal.ws.policy.AssertionSet;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Supplier;

public abstract class AbstractSingletonTest<S> {
    
    private final Supplier<S> singletonInstanceMethod;
    
    public AbstractSingletonTest(final Supplier<S> singletonInstanceMethod){
        this.singletonInstanceMethod=singletonInstanceMethod;
    }
    
    @Test
    public void testMultipeCallReturnTheSameObjectInSameThread(){
        S instance1 = this.singletonInstanceMethod.get();
        S instance2 = this.singletonInstanceMethod.get();
        S instance3 = this.singletonInstanceMethod.get();
        Assert.assertSame(instance1, instance2);
        Assert.assertSame(instance1, instance3);
        Assert.assertSame(instance2, instance3);
    }
    
    @Test
    public void testMultipleCallsReturnTheSameObjectInDifferentThreads() throws Exception {
       final List<Callable<S>> tasks = new ArrayList<>();
       
       for (int i=0; i < 10000; i++){
           tasks.add(this.singletonInstanceMethod :: get);
       }
       
       final ExecutorService executorService= Executors.newFixedThreadPool(8);
       final List<Future<S>> results=executorService.invokeAll(tasks);

        final S expectedInstance = this.singletonInstanceMethod.get();
        for (Future<S> res : results) {
            final S instance = res.get();
            Assert.assertNotNull(instance);
            Assert.assertSame(expectedInstance, instance);
        }
        
        executorService.shutdown();
    }
}
