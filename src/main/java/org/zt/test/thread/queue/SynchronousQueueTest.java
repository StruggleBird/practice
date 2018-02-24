package org.zt.test.thread.queue;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class SynchronousQueueTest {

	private ExecutorService executors = new ThreadPoolExecutor(20, 100, 60L, TimeUnit.SECONDS,
			new SynchronousQueue<Runnable>());

	private SynchronousQueue<Integer> queue = new SynchronousQueue<>();

	@Test
	public void testSubmit() throws InterruptedException {
		for (int i = 0; i < 101; i++) {
			executors.submit(new Runnable() {

				@Override
				public void run() {
					try {
						System.out.println(Thread.currentThread().getName() + " 执行");
						Thread.sleep(5 * 1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
		System.out.println(Thread.currentThread().getThreadGroup().activeCount() + "========");
		executors.shutdown();
		executors.awaitTermination(60, TimeUnit.SECONDS);

	}

	@Test
	public void test() throws IOException {
		new Thread(new Runnable() {

			@Override
			public void run() {
				int i = 0;
				while (true) {
					try {
						++i;
						System.out.println("pre put:" + i);
						queue.put(i);
						System.out.println("put:" + i);
						Thread.sleep(1000);
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
						System.out.println("pre take");
						System.out.println("take:" + queue.take()+"\n");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		System.in.read();
	}

}
