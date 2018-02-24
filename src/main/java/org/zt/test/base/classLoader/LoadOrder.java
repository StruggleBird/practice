package org.zt.test.base.classLoader;

public class LoadOrder {

	
	
	static {
		i = 456;
	}

	public static int i = 123;
	
	static {
		System.out.println(i);
	}
	
	
	
	public static void main(String[] args) {
		// 输出123 ，原因：静态代码块是按照从上到下顺序执行的,初始化阶段，i变量先被设置为456，后被设置为123
		// 参考：http://www.importnew.com/18548.html
	}
	
}
