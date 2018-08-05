package com.hobart.jvm.classes;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * 泛型的获取
 * @author hobart
 *
 */
public class TestGenerics {
	
	@Test
	public void test(){
		Map<String, Integer> map=new HashMap<String, Integer>(){};
		Type type = map.getClass().getGenericSuperclass();
		System.out.println(type.getTypeName());
		ParameterizedType cast = ParameterizedType.class.cast(type);
		Type[] typeArguments = cast.getActualTypeArguments();
		for(Type t:typeArguments){
			System.out.println(t.getTypeName());
			System.out.println(t.getClass());
			//System.out.println(t.toString());
		}
		
	}
	
}
