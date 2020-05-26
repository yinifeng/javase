package com.hubo.java.type;

import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.List;

public class GenericArrayTypeMain {
    public static void main(String[] args) throws NoSuchFieldException {
        Field fieldListArray = GenericArrayTypeTest.class.getDeclaredField("lists");
        Type typeListArray = fieldListArray.getGenericType();
        System.out.println("lists属性Type类型："+typeListArray.getTypeName());
        System.out.println("lists属性Type实际类型："+typeListArray.getClass().getName());
        GenericArrayType genericArrayType1 = (GenericArrayType) typeListArray;
        System.out.println("lists属性元素类型："+genericArrayType1.getGenericComponentType());
        System.out.println("lists属性元素Type类型："+genericArrayType1.getGenericComponentType().getClass().getName());

        System.out.println("----------------------------------------------------------------\n");
        Field fieldT = GenericArrayTypeTest.class.getDeclaredField("t");
        Type typeT = fieldT.getGenericType();
        System.out.println("t属性Type类型："+typeT.getTypeName());
        System.out.println("t属性Type实际类型："+typeT.getClass().getName());
        GenericArrayType genericArrayType2 = (GenericArrayType) typeT;
        System.out.println("t属性元素类型："+genericArrayType2.getGenericComponentType());
        System.out.println("t属性元素Type类型："+genericArrayType2.getGenericComponentType().getClass().getName());
    }
    
    public static class GenericArrayTypeTest<T>{
        private T[] t;
        
        private List<String>[] lists;
    }
}
