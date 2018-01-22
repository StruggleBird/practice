package test.thread.queue;

import java.util.Random;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class LinkedBlockingQueueTest {
	private ExecutorService executors = new ThreadPoolExecutor(100, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS,
			new LinkedBlockingQueue<Runnable>());

	private ExecutorCompletionService completionService = new ExecutorCompletionService(executors);

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

	/**
	 * 测试目的，防止内存泄露
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void testPoolCreated() throws InterruptedException {
		while (true) {
			executors = init();
			for (int i = 0; i < 100; i++) {
				final int taskId = i;
				executors.submit(new Runnable() {

					@Override
					public void run() {
						System.out.println(Thread.currentThread().getName() + " 执行 task:" + taskId);
						try {
							Thread.sleep(2);
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

	@SuppressWarnings("unchecked")
	@Test
	public void testPoolReuse() throws InterruptedException {
		while (true) {
			for (int i = 0; i < 100; i++) {
				final int taskId = i;
				completionService.submit(new Runnable() {

					@Override
					public void run() {
//						System.out.println(Thread.currentThread().getName() + " 执行 task:" + taskId);
						try {
							Thread.sleep(2);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}, null);
			}
			
			for (int i = 0; i < 100; i++) {
				completionService.take();
			}
			System.out.println("All task execution complated");

		}
	}

	private ExecutorService init() {
		executors = new ThreadPoolExecutor(100, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>());
		return executors;
	}

}
