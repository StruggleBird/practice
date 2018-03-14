package org.zt.test.concurrent;

import java.util.concurrent.LinkedBlockingDeque;

import org.junit.Test;

public class LinkedBlockingDequeTest {

	private LinkedBlockingDeque deque = new LinkedBlockingDeque<>();

	@Test
	public void testAddRemove() throws InterruptedException {

		for (int i = 0; i < 10; i++) {
			deque.add(i);
		}

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						Object last = deque.takeLast();
						Thread.sleep(10);
						System.out.println("Thread1:" + last);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						Object first = deque.takeFirst();
						Thread.sleep(10);
						System.out.println("Thread2:" + first);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

		Thread.sleep(1000);

	}

}
