package com.hobart.jiekou;

public interface MyInterface {
	
	default String getName(){
		return "哈哈哈";
	}
	
	public static void show(){
		System.out.println("接口中的静态方法");
	}
}
