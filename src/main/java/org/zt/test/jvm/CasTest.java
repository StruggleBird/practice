package org.zt.test.jvm;

import java.lang.reflect.Field;

import org.apache.commons.collections.map.StaticBucketMap;
import org.junit.Test;
import org.zt.sweet.utils.stat.Holder;
import org.zt.sweet.utils.stat.Stats;

import sun.misc.Unsafe;

public class CasTest {

	private static int threads = 4;

	private static final Unsafe unsafe;

	private static long countOffset;

	static {
		try {
			Field f = Unsafe.class.getDeclaredField("theUnsafe");
			f.setAccessible(true);
			unsafe = (Unsafe) f.get(null);
			countOffset = unsafe.objectFieldOffset(Counter.class.getDeclaredField("count"));

		} catch (Exception ex) {
			throw new Error(ex);
		}
	}

	public static void main(String[] args) throws Exception {

	}

	@Test
	public void testLock() throws InterruptedException {
		Counter counter = new CasTest().new Counter();
		Holder holder = Stats.start(() -> {
			boolean res = counter.lockIncreace();
//			System.out.println(res);
			if (!res) {
				throw new RuntimeException("fail");
			}
			return null;
		}, threads);

		holder.termination(100);
	}

	@Test
	public void testCas() throws InterruptedException {
		Counter counter = new CasTest().new Counter();

		Holder holder = Stats.start(() -> {
			boolean res = counter.casIncreace();
			if (!res) {
				throw new RuntimeException("fail");
			}
			return null;
		}, threads);

		holder.termination(100);
	}

	@Test
	public void ABTest() {
		Counter counter = new CasTest().new Counter();
		new Thread(() -> {
			long total = 0;
			long loop = 0;
			while (true) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				loop++;

				total += counter.count;
				System.out.println(counter.count + " avg:" + total / loop);
				boolean reset = counter.reset();
//				System.out.println("reset:" + reset);
			}

		}).start();

		for (int i = 0; i < threads; i++) {
			while (true) {
				counter.casIncreace();
//				counter.lockIncreace();
			}
		}
	}

	class Counter {
		private volatile long count = 0;

		public long getCount() {
			return count;
		}

		public void setCount(long count) {
			this.count = count;
		}

		public boolean casIncreace() {
			return unsafe.compareAndSwapLong(this, countOffset, count, count + 1);
		}

		public boolean reset() {
			while (!unsafe.compareAndSwapLong(this, countOffset, count, 0)) {

			}
			return true;
		}

		public synchronized boolean lockIncreace() {
			count++;
			return true;

		}

	}
}
