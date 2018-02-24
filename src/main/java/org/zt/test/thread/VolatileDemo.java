package org.zt.test.thread;

import java.util.concurrent.CountDownLatch;

public class VolatileDemo {

	private static int count;
	private static CountDownLatch countDownLatch = new CountDownLatch(1000);

	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 1000; i++) {
			new Thread() {
				@Override
				public void run() {
					count++;
					countDownLatch.countDown();
				}
			}.start();

		}

		countDownLatch.await();
		System.out.println(count);

	}

}
