package org.zt.test.thread.concurrent;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

import org.apache.commons.lang.math.RandomUtils;
import org.junit.Test;

public class CyclicBarrierTest {

	@Test
	public void testWait() throws InterruptedException {
		CyclicBarrier barrier = new CyclicBarrier(3);
		CountDownLatch cdl = new CountDownLatch(3);
		for (int i = 0; i < 3; i++) {

			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						System.out.println(Thread.currentThread().getName() + "准备就绪");
						long begin = System.currentTimeMillis();
						int sleepTime = new Random().nextInt(3000);
						Thread.sleep(sleepTime);
						System.out.println(Thread.currentThread().getName() + "进入屏障，待放行"
								+ (System.currentTimeMillis() - begin) + "ms");
						barrier.await();
						//所有线程休眠的时间可能不一样 但最终等待的时间应该是一致的，毕竟要互相等待
						System.out.println(Thread.currentThread().getName() + "放行,等待了"
								+ (System.currentTimeMillis() - begin) + "ms");
						cdl.countDown();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (BrokenBarrierException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}

		cdl.await();

	}
}
