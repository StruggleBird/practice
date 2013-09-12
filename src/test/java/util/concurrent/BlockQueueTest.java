package test.java.util.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import junit.framework.TestCase;

/**
 * @Project: test.commons
 * <p>Description:  </p>
 * <p>此模块分功能：</p>		
 * <p> 2013-9-11 </p>
 * @author Zhoutao
 * @version 2.x
 */
public class BlockQueueTest extends TestCase {

	BlockingQueue<Bar> bq;

	public void testAdd() throws InterruptedException
	{
		bq = new ArrayBlockingQueue<Bar>(2);
		Bar bar = new Bar("1", "zt");
		System.out.println(bq.add(bar));
		System.out.println(bq.size());
		Bar bar2 = new Bar("2", "zt");
		System.out.println(bq.offer(bar2));
		Bar bar3 = new Bar("3", "zt");
		System.out.println(bq.offer(bar3, 3L, TimeUnit.SECONDS));
	}
	
	public void testOffer() throws Exception {
		//新建一个线程、2秒后移除队列中起始元素
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				bq.remove();
			}
		}).start();
		Bar bar = new Bar("1", "zt");
		System.out.println(bq.add(bar));
		System.out.println(bq.size());
		Bar bar2 = new Bar("2", "zt");
		System.out.println(bq.offer(bar2));
		Bar bar3 = new Bar("3", "zt");
		//添加一个元素，如果集合已满，允许3秒的阻塞，3秒后还添加不进去，则添加失败，返回false
		System.out.println(bq.offer(bar3, 3L, TimeUnit.SECONDS)); 
		System.out.println(bq.size());

	}
	
	public void testPut() throws Exception {
		//新建一个线程、2秒后移除队列中起始元素
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				bq.remove();
			}
		}).start();
		Bar bar = new Bar("1", "zt");
		System.out.println(bq.add(bar));
		System.out.println("queue size:" + bq.size());
		Bar bar2 = new Bar("2", "zt");
		System.out.println(bq.offer(bar2));
		Bar bar3 = new Bar("3", "zt");
		bq.put(bar3);
		System.out.println("queue size:" + bq.size());
		System.out.println(bq.peek()); //一瞥，查看队列头部的元素，没有则返回null
		System.out.println("queue size:" + bq.size());
	}

	public void testRemove() throws Exception {
//		testAdd();
		System.out.println("---------remove------------");
		System.out.println("remove:" + bq.remove().getId());
		System.out.println("poll:" + bq.poll().getId());
		System.out.println(bq.size());
	}
	
	public void testDelayQueue() throws Exception {
//		testAdd();
		DelayQueue bq = new DelayQueue();
		Bar bar = new Bar("1", "zt");
		System.out.println("add:" + bq.add(bar));
		System.out.println("remove:" + bq.remove());
		System.out.println("poll:" + bq.poll());
		System.out.println(bq.size());
	}

	public static void main(String[] args) {

	}
}
