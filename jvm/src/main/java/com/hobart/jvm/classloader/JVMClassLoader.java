package com.hobart.jvm.classloader;

/**
 * 类加载器的分类
 *  C++实现的根类加载器
 * 	ExtClassLoader 扩展类加载器
 *  AppClassLoader 应用类加载器
 *  
 *  双亲委派模型
 *  
 *  
 * @author hobart
 *
 */
public class JVMClassLoader {
	public static void main(String[] args) {
		System.out.println(String.class.getClassLoader());
		System.out.println(JVMClassLoader.class.getClassLoader());
		System.out.println(JVMClassLoader.class.getClassLoader().getParent());
		System.out.println(JVMClassLoader.class.getClassLoader().getParent().getParent());
	}
}
