package com.hubo.java.enums;

import java.util.EnumMap;
import java.util.EnumSet;

public enum AnimalEnum {
    CAT() {
        @Override
        void sayHello() {
            System.out.println("喵喵....");
        }
    },
    DOG() {
        @Override
        void sayHello() {
            System.out.println("汪汪...");
        }
    };
    
    abstract void sayHello();

    public static void main(String[] args) {
        EnumSet enSets=EnumSet.allOf(AnimalEnum.class);
        enSets.remove(CAT);
        System.out.println(enSets);

        EnumMap<AnimalEnum,String> enMap=new EnumMap<AnimalEnum, String>(AnimalEnum.class);
        enMap.put(DOG, "dog");
        System.out.println(enMap);
    }
}
