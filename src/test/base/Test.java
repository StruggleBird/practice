package test.base;

import java.util.Map;
import java.util.TreeMap;

public class Test {

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
		return map.lowerEntry(random).getValue();

	}

}
