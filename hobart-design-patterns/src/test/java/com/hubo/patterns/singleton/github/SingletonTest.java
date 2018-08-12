package com.hubo.patterns.singleton.github;

import org.junit.Test;

public class SingletonTest {

    /**
     * 枚举测试
     */
    @Test
    public void testEnumInstance(){
        EnumIvoryTower e1=EnumIvoryTower.INSTANCE;
        EnumIvoryTower e2=EnumIvoryTower.INSTANCE;
        System.out.println(e1.toString());
        System.out.println(e2.toString());
        System.out.println(e1 == e2);
    }

    /**
     * 内部类测试
     */
    @Test
    public void testHolderInstance(){
        InitializingOnDemandHolderIdiom i1 = InitializingOnDemandHolderIdiom.getInstance();
        InitializingOnDemandHolderIdiom i2 = InitializingOnDemandHolderIdiom.getInstance();

        System.out.println(i1.toString());
        System.out.println(i2.toString());
        System.out.println(i1 == i2);
    }
    
    @Test
    public void testIvoryTower(){
        IvoryTower i1 = IvoryTower.getInstance();
        IvoryTower i2 = IvoryTower.getInstance();

        System.out.println(i1.toString());
        System.out.println(i2.toString());
        System.out.println(i1 == i2);
    }
    
    @Test
    public void testDoubleCheckLocking(){
        ThreadSafeDoubleCheckLocking i1 = ThreadSafeDoubleCheckLocking.getInstance();
        ThreadSafeDoubleCheckLocking i2 = ThreadSafeDoubleCheckLocking.getInstance();

        System.out.println(i1);
        System.out.println(i2);
        System.out.println(i1 == i2);
    }
}