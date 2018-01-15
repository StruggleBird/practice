package test.thread.queue;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class LinkedBlockingQueueTest {
	private ExecutorService executors = new ThreadPoolExecutor(100, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS,
			new LinkedBlockingQueue<Runnable>());

	@Test
	public void testSubmit() throws InterruptedException {
		for (int i = 0; i < 1000; i++) {
			final int taskId = i;
			executors.submit(new Runnable() {

				@Override
				public void run() {
					try {
						System.out.println(Thread.currentThread().getName() + " 执行 task:" + taskId);
						Thread.sleep(new Random().nextInt(5) * 1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
		System.out.println(Thread.currentThread().getThreadGroup().activeCount() + "========");
		executors.shutdown();
		executors.awaitTermination(10, TimeUnit.SECONDS);

	}

}
