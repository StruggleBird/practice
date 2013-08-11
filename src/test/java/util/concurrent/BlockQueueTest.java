package test.java.util.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

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
	
	BlockingQueue<Bar> bq = new LinkedBlockingDeque<Bar>();
	
	public void testAdd()
	{
		Bar bar = new Bar("1","zt");
		System.out.println(bq.add(bar));;
		System.out.println(bq.size());
		Bar bar2 = new Bar("2","zt");
		System.out.println(bq.offer(bar2));
		Bar bar3 = new Bar("3","zt");
		System.out.println(bq.offer(bar3));
		/*Bar bar4 = new Bar("3","zt");
		System.out.println(bq.add(bar4));*/
		System.out.println(bq.size());
	}
	
	public void testRemove(){
		testAdd();
		System.out.println("---------remove------------");
		System.out.println("remove:"+bq.remove().getId());
		System.out.println("poll:" + bq.poll().getId());
		System.out.println(bq.size());
		
	}
	
	public static void main(String[] args) {
		
	}
}
