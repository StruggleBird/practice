package org.zt.test.map;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class HashMapTest {

	/**
	 * 如果在使用迭代器的过程中有其他线程修改了map，那么将抛出ConcurrentModificationException，这就是所谓fail-fast策略。
	 * 这个异常意在提醒开发者及早意识到线程安全问题
	 */
	@Test
	public void fastFail() {
		Map<String, String> map = new HashMap<>();
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
	 */
	@Test
	public void resize() {
		Map<Integer, Integer> map = new HashMap<>(2);
		map.put(3, 3);
		map.put(7, 7);
		map.put(5, 5);
		for (Integer key : map.keySet()) {
			System.out.println(key);
		}
	}

}
