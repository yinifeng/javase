package com.hubo.java.reflection;

import java.util.Arrays;

/**
 * java.lang.Class类的几个关键方法:
 * 
 *   getPackage()	返回包的路径
 *   getName()	返回类的名称
 *   getSuperclass()	返回父类
 *   getInterfaces()	返回其实现的接口
 *   getConstructors()	返回其所有的public构造方法
 *   getConstructors(Class… parameterTypes)	返回其指定参数的public构造方法
 *   getDeclaredConstructors()	返回其所有声明的构造方法，包括private构造方法
 *   getDeclaredConstructors(Class… parameterTypes)	返回其指定参数的构造方法，包括private构造方法
 *   getMethods()	返回其所有的public的方法，包括其父类的public方法
 *   getMethod(String name, Class… parameterTypes)	返回其指定的public的方法
 *   getDeclaredFields()	返回其声明的所有的方法，包括private类型的方法
 *   getDeclaredMethod(String name, Class… parameterTypes)	返回其指定参数的方法，包括private类型的方法
 *   getFields()	返回其所有的public属性，包括其父类的public属性
 *   getField(String name)	返回其指定的public属性，包括其父类的public属性
 *   getDeclaredFields()	返回其声明的所有的属性，包括其private类型的属性
 *   getDeclaredField(String name)	返回其指定的属性，包括其private类型的属性
 *   getClasses()	返回其public的内部类
 *   getDeclaredClasses()	返回其所有的内部类，包括private类型的内部类
 * @author hobart
 *
 */
public class ReflectionTest {
	public static void main(String[] args) {
		Class<Cat> clazz=Cat.class;
		Class<?>[] classes = clazz.getDeclaredClasses();
		System.out.println(Arrays.toString(classes));
		//直接接口
		System.out.println(Arrays.toString(clazz.getInterfaces()));
		System.out.println(clazz.getSuperclass());
		
		//System.out.println(clazz.);
	}
}
