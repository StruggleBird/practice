package org.zt.test.map;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class HashMapTest {

	/**
	 * 如果在使用迭代器的过程中有其他线程修改了map，那么将抛出ConcurrentModificationException，这就是所谓fail-fast策略。
	 * 这个异常意在提醒开发者及早意识到线程安全问题
	 */
	@Test
	public void fastFail() {
		final Map<String, String> map = new HashMap<>();
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 1000000; i++) {
					map.put(i + "", i + "");
				}
			}
		}).start();

		for (String key : map.keySet()) {

		}
		System.out.println(map.size());

	}

	/**
	 * resize 死循环: http://blog.csdn.net/hll174/article/details/50915346
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void resize() throws InterruptedException {

		final ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>(2);
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10000; i++) {
					new Thread(new Runnable() {
						@Override
						public void run() {
							map.put(UUID.randomUUID().toString(), "");
						}
					}, "ftf" + i).start();
				}
			}
		}, "ftf");
		t.start();
		t.join();
		Thread.sleep(3000);
		System.out.println(map.size());
	}

}
