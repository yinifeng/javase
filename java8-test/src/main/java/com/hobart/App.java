package com.hobart;

import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		
		Map<String, String> map=new HashMap<>();
		map.put("aaa", "111");
		map.put("bbb", "222");
		map.forEach((s1,s2)->System.out.println(s1+"="+s2));
	}
}
