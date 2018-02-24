package org.zt.test.thread;

import java.io.IOException;

import org.junit.Test;

public class ThreadLocalTest {

	private static final ThreadLocal<Integer> local1 = new ThreadLocal<>();
	private static final ThreadLocal<Integer> local2 = new InheritableThreadLocal<>();

	@Test
	public void testThreadLocal() throws IOException {
		local1.set(1);
		new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println(local1.get());
			}
		}).start();
		System.in.read();
	}

	@Test
	public void testInheritableThreadLocal() throws IOException {
		local2.set(1);
		new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println(local2.get());
			}
		}).start();
		System.in.read();
	}
}
