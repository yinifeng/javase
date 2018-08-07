package com.hubo.java.reflection;

public class Cat extends ChongWu implements Animal,Xingwei {
	private int tui;
	private String name;
	
	public Cat() {
		this("猫",4);
	}
	
	public Cat(String name,int tui) {
		this.tui=tui;
		this.name=name;
	}


	@Override
	public void eat(String foot) {
		System.out.println(name+"吃"+foot);
	}

	@Override
	public int count() {
		return this.tui;
	}

	@Override
	public void jiao() {
		System.out.println("喵喵...");
	}

}
