package test.base;

import java.util.Random;

public class RandomFileName {
	/**
	 * 产生一个随机的字符串
	 * 
	 * @param 字符串长度
	 * @return
	 */

	public static String getRandomString(int length) {
		String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(RandomFileName.getRandomString(10));
	}
}