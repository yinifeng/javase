package com.hobart.jvm.classes;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class MyConvert<S,T> {
	private Class<?> srcClazz;
	private Class<?> destClazz;
	
	public MyConvert() {
		Type type = this.getClass().getGenericSuperclass();
		System.out.println(type.getTypeName());
		ParameterizedType cast = ParameterizedType.class.cast(type);
		Type[] typeArguments = cast.getActualTypeArguments();
		srcClazz=typeArguments[0].getClass();
		destClazz=typeArguments[1].getClass();
		for(Type t:typeArguments){
			System.out.println(t.getTypeName());
			System.out.println(t.getClass());
			//System.out.println(t.toString());
		}
	}
	
	protected abstract void show();
	
}
