package org.zt.test.thread.lock;

import org.junit.Test;

public class SynchronizedDemo {
	/**
	 * javap -c SynchronizedDemo.class 查看字节码
	 */
	@Test
    public void method() {
        synchronized (this) {
            System.out.println("Method 1 start");
        }
    }
}