package test.data.structure;

import org.apache.commons.lang.math.RandomUtils;
import org.junit.Test;
import org.zt.sweet.utils.stat.Holder;
import org.zt.sweet.utils.stat.Stats;

public class RingBufferTest {

	@Test
	public void testRingBuffer() throws InterruptedException {
		final RingBuffer ringBuffer = new RingBuffer(10240);

		new Thread(new Runnable() {

			@Override
			public void run() {

				while (true) {

					String value = "1" + RandomUtils.nextInt(10000);
					ringBuffer.put(value);
					System.out.println("put vallue:" + value);
				}
			}
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					String value = ringBuffer.get();
					if (value == null) {
						try {
							Thread.sleep(1);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.println("get:" + value);
				}
			}
		}).start();

		Thread.currentThread().join();
		//
		//
		// Holder holder = Stats.start(new Runnable() {
		//
		// @Override
		// public void run() {
		// String value = "1" + RandomUtils.nextInt(10000);
		// Boolean put = ringBuffer.put(value);
		// if (put) {
		// String val = ringBuffer.get();
		// if (!val.equals(value)) {
		// System.out.println("error");
		// }
		// }
		// try {
		// Thread.sleep(10);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// }
		// }, 100);
		// holder.termination(30);
	}
}
