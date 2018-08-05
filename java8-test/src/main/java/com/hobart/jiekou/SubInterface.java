package com.hobart.jiekou;

public class SubInterface implements MyInterface,MyFun{

	@Override
	public String getName() {
		//接口名调用实现
		return MyInterface.super.getName();
	}

}
