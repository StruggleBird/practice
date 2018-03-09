package org.zt.test.map;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

public class ConcurrentHashMapTest {

	@Test
	public void newInstance() {
		ConcurrentHashMap map = new ConcurrentHashMap();
	}

	/**
	 * 测试java8中 size的精准度
	 * @throws InterruptedException
	 */
	@Test
	public void testSize() throws InterruptedException {
		final ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
		final int maxSize = 10000000;
		ExecutorService pool = Executors.newFixedThreadPool(1000);
		long begin = System.currentTimeMillis();

		for (int i = 0; i < 1000; i++) {
			pool.submit(new Runnable() {

				@Override
				public void run() {
					while (true) {
						if (map.size() >= maxSize)
							return;

						String key = UUID.randomUUID().toString();
						map.put(key, 1); // 业务处理

						if (map.size() > maxSize) {
							map.remove(key); // 取消逻辑
						}

					}
				}
			});
		}

		pool.shutdown();
		boolean awaitTermination = pool.awaitTermination(600, TimeUnit.SECONDS);
		long duration = System.currentTimeMillis() - begin;
		System.out.println(awaitTermination ? "执行完成,耗时：" + duration + "(ms)" : "执行超时");
		System.out.println(map.size());
	}

}
