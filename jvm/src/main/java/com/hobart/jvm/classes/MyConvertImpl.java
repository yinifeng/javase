package com.hobart.jvm.classes;

public class MyConvertImpl extends MyConvert<String, Integer>{

	@Override
	protected void show() {
		// TODO Auto-generated method stub
		System.out.println("hello...........");
	}
	
	public static void main(String[] args) {
		MyConvert<String, Integer> m=new MyConvertImpl();
		m.show();
	}
}
