package com.hubo.java.enums;

public enum StatusEnum {
	NONE(0),SUCESS(1),ERROR(2);
	
	private int code;
	
	private StatusEnum(int code){
		this.code=code;
	}
	
	public int getCode(){
		return this.code;
	}
	
	public static StatusEnum getStatus(int code){
		StatusEnum[] values = StatusEnum.values();
		StatusEnum v = StatusEnum.valueOf("ERROR");
		System.out.println(v);
		for (StatusEnum statusEnum : values) {
			if (statusEnum.code == code) {
				System.out.println(statusEnum.code);
				return statusEnum;
			}
		}
		return null;
	}
	
}
