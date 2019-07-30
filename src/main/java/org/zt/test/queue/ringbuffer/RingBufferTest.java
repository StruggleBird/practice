package org.zt.test.queue.ringbuffer;

import java.util.concurrent.Callable;

import org.apache.commons.lang.math.RandomUtils;
import org.junit.Test;
import org.zt.sweet.utils.stat.Holder;
import org.zt.sweet.utils.stat.Stats;

public class RingBufferTest {

	@Test
	public void testRingBuffer() throws InterruptedException {
		final RingBuffer ringBuffer = new RingBuffer(10240);


		Holder holder = Stats.start(new Callable() {

			@Override
			public Object call() {
				String value = "1";
				Boolean put = ringBuffer.put(value);
				String val = ringBuffer.get();
				
				return null;
			}
		}, 500);
		holder.termination(30);
	}
}
