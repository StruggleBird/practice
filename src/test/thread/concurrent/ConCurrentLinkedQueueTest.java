/**
 * 
 */
package test.thread.concurrent;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.junit.Test;

/**
 * @author Zhoutao
 * @create 2014-8-31
 */
public class ConCurrentLinkedQueueTest {

	private static ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<Integer>();

	@Test
	public void testOffer() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, NoSuchFieldException, SecurityException {
		queue.add(1);
		queue.add(2);
		queue.add(3);
	}

}
