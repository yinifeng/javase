package com.hubo.java.type;

import java.lang.reflect.Field;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.List;

public class TypeVariableMain {
    public static void main(String[] args) throws NoSuchFieldException {
        Field aField = TypeVariableTest.class.getDeclaredField("a");
        Type aFieldType = aField.getGenericType();
        Class aFieldClass = aField.getType();
        System.out.println("TypeVariableTest<T> a 域 type名："+aFieldType.getTypeName());
        System.out.println("TypeVariableTest<T> a 域 type的class名："+aFieldType.getClass().getCanonicalName());
        System.out.println("TypeVariableTest<T> a 域 Class名: "+aFieldClass.getCanonicalName());

        if (aFieldType instanceof TypeVariable){
            TypeVariable typeVariable = (TypeVariable) aFieldType;
            String name = typeVariable.getName();
            Type[] bounds = typeVariable.getBounds();
            System.out.println("a 域类型是 TypeVariable类型！");
            System.out.println("a 域 type'name: "+name);
            for (int i=0;i<bounds.length;i++){
                System.out.println("a 域 type'bounds["+i+"] Type'name: "+bounds[i].getTypeName());
            }
            GenericDeclaration genericDeclaration = typeVariable.getGenericDeclaration();
            System.out.println("声明a 域变量的实体："+genericDeclaration);
        }

        System.out.println("-----------------------------------------------------------\n");
        Field bField  = TypeVariableTest.class.getDeclaredField("b");
        Type bFieldType = bField.getGenericType();
        Class bFieldClass = bField.getType();
        System.out.println("TypeVariableTest<T> b 域 type名："+bFieldType.getTypeName());
        System.out.println("TypeVariableTest<T> b 域 type的Class名："+bFieldType.getClass().getCanonicalName());
        System.out.println("TypeVariableTest<T> b 域 Class名："+bFieldClass.getCanonicalName());

    }

    public static class TypeVariableTest<T> {
        T a;
        List<String> b;
    }
}
