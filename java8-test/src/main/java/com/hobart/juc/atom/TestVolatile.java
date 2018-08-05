package com.hobart.juc.atom;

public class TestVolatile {
	public static void main(String[] args) {
		ThreadDemo td = new ThreadDemo();
		new Thread(td).start();

		while (true) {
			if (td.isFlg()) {
				System.out.println("##############");
				break;
			}
		}
	}
}

class ThreadDemo implements Runnable {
	// private boolean flg=false;
	private volatile boolean flg = false;

	@Override
	public void run() {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		flg = true;
		System.out.println("flg=" + isFlg());
	}

	public boolean isFlg() {
		return flg;
	}

	public void setFlg(boolean flg) {
		this.flg = flg;
	}

}
