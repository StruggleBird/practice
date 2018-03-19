package org.zt.test.thread.queue;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class SynchronousQueueTest {

	private SynchronousQueue<Integer> queue = new SynchronousQueue<>();

	@Test
	public void testSubmit() throws InterruptedException {

		ExecutorService executors = new ThreadPoolExecutor(20, 100, 60L, TimeUnit.SECONDS,
				new LinkedBlockingDeque<Runnable>(20));
		// 最多100个线程，当第101个任务提交时会被拒绝
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
						System.out.println("take:" + queue.take() + "\n");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		System.in.read();
	}

	/**
	 * 生产者、消费者互相阻塞示例
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void testFair() throws InterruptedException {
		long begin = System.currentTimeMillis();
		SynchronousQueue<Object> queue = new SynchronousQueue<>(true); // 公平锁体现在入队顺序和出队顺序是否一致
		// fair 为true时，按照线程插入的先后顺序入队；为false时，则为竞争机制
		CountDownLatch cdl = new CountDownLatch(20);
		// 消费线程
		new Thread(new Runnable() {

			@Override
			public void run() {
				Thread t = null;
				try {
					Thread.sleep(100);
					while ((t = (Thread) queue.take()) != null) {
						System.out.println("	" + t.getName() + "出队列");
						cdl.countDown();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();

		// 生产线程
		for (int i = 0; i < 10; i++) {
			final String name = i + "";
			new Thread(new Runnable() {

				@Override
				public void run() {
					Thread.currentThread().setName(name);
					try {
						queue.put(Thread.currentThread());
						System.out.println(name + "加入队列");
						cdl.countDown();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
			Thread.sleep(1); // 休眠1秒，保证线程入队顺序
		}

		cdl.await();
		System.out.println("测试结束耗时：" + (System.currentTimeMillis() - begin) + "ms");
	}

}
