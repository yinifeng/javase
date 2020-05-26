package com.hubo.java.type;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.List;

public class WildcardTypeTest {
    public static void main(String[] args) throws NoSuchFieldException {
        Field aField = TypeVariableTest.class.getDeclaredField("a");
        Type aFieldType = aField.getGenericType();
        Class aFieldClass = aField.getType();
        System.out.println("TypeVariableTest a 域 type名："+aFieldType.getTypeName());
        System.out.println("TypeVariableTest a 域 type的class名："+aFieldType.getClass().getCanonicalName());
        System.out.println("TypeVariableTest a 域 Class名: "+aFieldClass.getCanonicalName());

        if (aFieldType instanceof ParameterizedType){
            ParameterizedType parameterizedType = (ParameterizedType) aFieldType;
            System.out.println("a 域Type 是 ParameterizedType ！");
            Type[] typesString = parameterizedType.getActualTypeArguments();
            System.out.println("a 域泛型参数[0]的type名："+typesString[0].getTypeName());
            System.out.println("a 域泛型参数[0]的type类型名："+typesString[0].getClass().getCanonicalName());

            WildcardType type0 = (WildcardType) typesString[0];
            Type[] type0UpperBounds = type0.getUpperBounds();
            Type[] type0LowerBounds = type0.getLowerBounds();

            System.out.println("泛型参数[0]的上限type:"+type0UpperBounds[0]);
        }
    }

    public static class TypeVariableTest {
        List<? extends String> a;
    }
}
