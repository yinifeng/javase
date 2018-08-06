package com.hubo.java.enums;

public class TestEnum {
	public static void main(String[] args) {
		System.out.println(StatusEnum.getStatus(0) == StatusEnum.NONE);
		System.out.println(StatusEnum.SUCESS);
		System.out.println(StatusEnum.ERROR.getCode());
		
		System.out.println("---------------------------------------");
		System.out.println(NetworkInterfaceManager.INSTANCE.getLocalHostName());
		System.out.println(NetworkInterfaceManager.INSTANCE.getLocalHostAddress());
	}
}
