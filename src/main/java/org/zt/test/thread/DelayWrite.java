package org.zt.test.thread;

public class DelayWrite implements Runnable {
	private volatile String str;

	void setStr(String str) {
		this.str = str;
	}

	public void run() {
		while (str == null)
			;
		System.out.println(str);
	}

	public static void main(String[] args) throws InterruptedException {
		DelayWrite delay = new DelayWrite();
		new Thread(delay).start();
		Thread.sleep(500);
		delay.setStr("Hello world1!!");
		Thread.sleep(10);
		delay.setStr("Hello world2!!");
	}
	
	
}