package org.zt.test.map;

import java.util.TreeMap;

public class TreeMapTest {

	public static void main(String[] args) {

		System.out.println(random());

	}
	
	

	private static String random() {
		TreeMap<Integer, String> map = new TreeMap<Integer, String>();
		map.put(0, "A");
		map.put(30, "B");
		map.put(80, "C");
		map.put(100, "D");
		map.put(140, "E");
		int random = (int) (Math.random() * 150);
		System.out.println(random);
		return map.lowerEntry(random).getValue();

	}

}
