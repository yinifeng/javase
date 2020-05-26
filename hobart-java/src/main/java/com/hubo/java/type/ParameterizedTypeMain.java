package com.hubo.java.type;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ParameterizedTypeMain {

    public static void main(String[] args) throws Exception{
        //获取ParameterizedTypeTest类的list属性
        Field fieldList = ParameterizedTypeTest.class.getDeclaredField("list");
        //Field fieldList = stringParameterizedTypeTest.getClass().getDeclaredField("list");
        //获取该属性的泛型类型
        Type typeList = fieldList.getGenericType();
        System.out.println("list属性类型名:"+typeList.getTypeName());
        System.out.println("list属性实际Type类型："+typeList.getClass().getName());
        
        System.out.println("----------------------------------------------------------------\n");
        
        //获取ParameterizedTypeTest类的set属性
        Field fieldSet = ParameterizedTypeTest.class.getDeclaredField("set");
        //获取该属性的泛型类型
        Type typeSet = fieldSet.getGenericType();
        System.out.println("set属性类型名:"+typeSet.getTypeName());
        System.out.println("set属性实际Type类型："+typeSet.getClass().getName());
        
        System.out.println("----------------------------------------------------------------\n");

        //获取ParameterizedTypeTest类的map属性
        Field fieldMap = ParameterizedTypeTest.class.getDeclaredField("map");
        //获取该属性的泛型类型
        Type typeMap = fieldMap.getGenericType();
        System.out.println("map属性类型名:"+typeMap.getTypeName());
        System.out.println("map属性实际Type类型："+typeMap.getClass().getName());
        if (typeMap instanceof ParameterizedType) {
            ParameterizedType mapParameterizedType = (ParameterizedType) typeMap;
            //获取泛型中的实际参数
            Type[] actualTypeArguments = mapParameterizedType.getActualTypeArguments();
            System.out.println("map属性泛型参数类型[0]:"+actualTypeArguments[0]);
            System.out.println("map属性泛型参数类型[1]:"+actualTypeArguments[1]);
            System.out.println("map属性泛型参数的类类型:"+mapParameterizedType.getRawType());
            System.out.println("泛型拥有者类型:"+mapParameterizedType.getOwnerType());
        }

        System.out.println("----------------------------------------------------------------\n");
        
        //获取ParameterizedTypeTest类的map属性
        Field fieldMap2 = ParameterizedTypeTest.class.getDeclaredField("map2");
        //获取该属性的泛型类型
        Type typeMap2 = fieldMap2.getGenericType();
        System.out.println("map2属性类型名:"+typeMap2.getTypeName());
        System.out.println("map2属性实际Type类型："+typeMap2.getClass().getName());
        if (typeMap2 instanceof ParameterizedType) {
            ParameterizedType mapParameterizedType2 = (ParameterizedType) typeMap2;
            //获取泛型中的实际参数
            Type[] actualTypeArguments2 = mapParameterizedType2.getActualTypeArguments();
            System.out.println("map2属性泛型参数类型[0]:"+actualTypeArguments2[0]);
            System.out.println("map2属性泛型参数类型[1]:"+actualTypeArguments2[1]);
            System.out.println("map2属性泛型参数的类类型:"+mapParameterizedType2.getRawType());
            System.out.println("泛型拥有者类型:"+mapParameterizedType2.getOwnerType());
        }
    }


    public static class ParameterizedTypeTest<T>{
        private List<T> list;
        private Set<T> set;
        private Map<String ,T> map;
        private Map.Entry<String,Integer>  map2;
    }
}
