package com.hubo.java.resource;

public class Resource {
	public static void main(String[] args) {
		//1.通过class的getResource方法
		String a1 = ResourceTest.class.getResource("/com/hubo/java/resource/Resource.class").getPath();
		String a2 = ResourceTest.class.getResource("Resource.class").getPath();
		String a3 = ResourceTest.class.getResource("/request.xml").getPath();
		String a4 = ResourceTest.class.getResource("../../../../request.xml").getPath();
		String a5 = ResourceTest.class.getResource("/conf/sysConf.json").getPath();
		String a6 = ResourceTest.class.getResource("../../../../conf/sysConf.json").getPath();
		System.out.println("a1:"+a1);
		System.out.println("a2:"+a2);
		System.out.println("a3:"+a3);
		System.out.println("a4:"+a4);
		System.out.println("a5:"+a5);
		System.out.println("a6:"+a6);
		System.out.println("-------------------------------------");
		
		//2.通过本类的ClassLoader的getResource方法
		String b1 = ResourceTest.class.getClassLoader().getResource("com/hubo/java/resource/Resource.class").getPath();
		String b2 = ResourceTest.class.getClassLoader().getResource("request.xml").getPath();
		String b3 = ResourceTest.class.getClassLoader().getResource("conf/sysConf.json").getPath();
		System.out.println("b1:"+b1);
		System.out.println("b2:"+b2);
		System.out.println("b3:"+b3);
		System.out.println("-------------------------------------");
		
		//3.通过ClassLoader的getSystemClassLoader的getResource方法
		String c1 = ClassLoader.getSystemClassLoader().getResource("com/hubo/java/resource/Resource.class").getPath();
		String c2 = ClassLoader.getSystemClassLoader().getResource("request.xml").getPath();
		String c3 = ClassLoader.getSystemClassLoader().getResource("conf/sysConf.json").getPath();
		System.out.println("c1:"+c1);
		System.out.println("c2:"+c2);
		System.out.println("c3:"+c3);
		System.out.println("-------------------------------------");
		
		//4.通过ClassLoader的getSystemResource方法
		String d1 = ClassLoader.getSystemResource("com/hubo/java/resource/Resource.class").getPath();
		String d2 = ClassLoader.getSystemResource("request.xml").getPath();
		String d3 = ClassLoader.getSystemResource("conf/sysConf.json").getPath();
		System.out.println("d1:"+d1);
		System.out.println("d2:"+d2);
		System.out.println("d3:"+d3);
		System.out.println("-------------------------------------");
		
		//5.通过Thread方式加载
		String e1 = Thread.currentThread().getContextClassLoader().getResource("com/hubo/java/resource/Resource.class").getPath();
		String e2 = Thread.currentThread().getContextClassLoader().getResource("request.xml").getPath();
		String e3 = Thread.currentThread().getContextClassLoader().getResource("conf/sysConf.json").getPath();
		System.out.println("e1:"+e1);
		System.out.println("e2:"+e2);
		System.out.println("e3:"+e3);
	}
}
