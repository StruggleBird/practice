package org.zt.test.thread.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 *
 * @author Zhoutao
 * @date 2014年9月11日
 */
public class SemaphoreTest {

	/**
	 * 通过信号量限流
	 * @throws InterruptedException 
	 */
	@Test
	public void test1() throws InterruptedException {
		final int THREAD_COUNT = 30;
		ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);//30个线程
		java.util.concurrent.Semaphore s = new java.util.concurrent.Semaphore(10); //最多同时处理10个请求
		for (int i = 0; i < THREAD_COUNT; i++) {
			final String name = i+"";
			threadPool.execute(new Runnable() {
				@Override
				public void run() {
					try {
						s.acquire();
						System.out.println("save data:"+name);
						Thread.sleep(1000);
						s.release();
					} catch (InterruptedException e) {
					}
				}
			});
		}
		threadPool.shutdown();
		threadPool.awaitTermination(10, TimeUnit.MINUTES);

	}

	@Test
	public void testSemaphore() {
		Semaphore semaphore = new Semaphore();

		SendingThread sender = new SendingThread(semaphore);

		ReceivingThread receiver = new ReceivingThread(semaphore);

		receiver.start();
		sender.start();
	}

	/**
	 * 可计数的Semaphore
	 *
	 * @author Zhoutao
	 * @date 2014年9月11日
	 * @throws InterruptedException
	 */
	@Test
	public void testCountingSemaphore() throws InterruptedException {
		CountingSemaphore semaphore = new CountingSemaphore();

		SendingThread sender = new SendingThread(semaphore);

		ReceivingThread receiver = new ReceivingThread(semaphore);

		receiver.start();
		sender.start();

		Thread.sleep(50000);
	}

	/**
	 * 有上限的信号量
	 * 
	 * @author Zhoutao
	 * @date 2014年9月11日
	 * @throws InterruptedException
	 */
	@Test
	public void testBoundedSemaphore() throws InterruptedException {
		BoundedSemaphore semaphore = new BoundedSemaphore(5);

		SendingThread sender = new SendingThread(semaphore);

		ReceivingThread receiver = new ReceivingThread(semaphore);

		receiver.start();
		sender.start();

		Thread.sleep(50000);
	}

}

class Semaphore {

	private volatile boolean signal = false;

	public synchronized void take() throws InterruptedException {

		this.signal = true;

		this.notify();

	}

	public synchronized void release() throws InterruptedException {

		while (!this.signal)
			wait();

		this.signal = false;

	}

}

class CountingSemaphore extends Semaphore {

	private int signals = 0;

	public synchronized void take() {

		this.signals++;

		this.notify();

	}

	public synchronized void release() throws InterruptedException {

		while (this.signals == 0)
			wait();

		this.signals--;

	}

}

class BoundedSemaphore extends Semaphore {

	private int signals = 0;

	private int bound = 0;

	public BoundedSemaphore(int upperBound) {

		this.bound = upperBound;

	}

	public synchronized void take() throws InterruptedException {

		while (this.signals == bound)
			wait();

		this.signals++;
		System.out.println(signals);

		this.notify();

	}

	public synchronized void release() throws InterruptedException {

		while (this.signals == 0)
			wait();

		this.signals--;

		this.notify();

	}

}

class ReceivingThread extends Thread {

	Semaphore semaphore = null;

	public ReceivingThread(Semaphore semaphore) {

		this.semaphore = semaphore;

	}

	public void run() {

		while (true) {

			try {
				System.out.println("before release");
				this.semaphore.release();
				System.out.println("release..");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// receive signal, then do something...

		}

	}

}

class SendingThread extends Thread {

	Semaphore semaphore = null;

	public SendingThread(Semaphore semaphore) {

		this.semaphore = semaphore;

	}

	public void run() {

		while (true) {
			try {
				// do something, then signal
				System.out.println("before take");
				this.semaphore.take();
				System.out.println("take..");

				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}
