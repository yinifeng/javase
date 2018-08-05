package com.hubo.java.config;

import java.util.Arrays;
import java.util.Map;
import java.util.Properties;

public class LoadProperties {
	
	public static void main(String[] args) {
		//argumets 
		System.out.println(Arrays.toString(args));
		System.out.println("-------------------------------");
		Map<String, String> mapEnv = System.getenv();
//		for(Map.Entry<String, String> entry:mapEnv.entrySet()){
//			System.out.println(entry.getKey()+"<====>"+entry.getValue());
//		}
		//environment 
		System.out.println(System.getenv("haha"));
		
		System.out.println("-------------------------------");
		Properties properties = System.getProperties();
		
		//VM argumets  java -jar ./xxx.jar -Dhubo=http
		System.out.println(System.getProperty("hubo"));
		System.out.println(System.getProperty("haha"));
	}
	

}
