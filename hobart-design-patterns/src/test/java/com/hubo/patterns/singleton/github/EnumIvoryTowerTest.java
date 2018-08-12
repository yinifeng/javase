package com.hubo.patterns.singleton.github;

import org.junit.Test;

import static org.junit.Assert.*;

public class EnumIvoryTowerTest {
    
    @Test
    public void testEnumInstance(){
        EnumIvoryTower e1=EnumIvoryTower.INSTANCE;
        EnumIvoryTower e2=EnumIvoryTower.INSTANCE;
        System.out.println(e1.toString());
        System.out.println(e2.toString());
        System.out.println(e1 == e2);
    }
}