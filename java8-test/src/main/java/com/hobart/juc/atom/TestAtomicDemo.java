package com.hobart.juc.atom;

import java.util.concurrent.atomic.AtomicInteger;

public class TestAtomicDemo {
	public static void main(String[] args) {
		AtomicDemo ad=new AtomicDemo();
		for(int i=0;i<10;i++){
			new Thread(ad).start();
		}
	}
}

class AtomicDemo implements Runnable{
	//private int serialNumber=0;
	AtomicInteger serialNumber=new AtomicInteger(0);
	
	@Override
	public void run() {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+":"+getSerialNumber());
	}

	public int getSerialNumber() {
		//return serialNumber++;
		return serialNumber.getAndIncrement();
	}
	
	
}