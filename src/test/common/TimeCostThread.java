/**
 * 
 */
package test.common;

import java.util.Date;

/**
 * @author Zhoutao
 * @create 2014年9月7日
 */
public class TimeCostThread extends Thread {

	@Override
	public void run() {
		Date start = new Date();
		System.out.println("Task start...");
		synchronized (TimeCostThread.class) {
			try {
				TimeCostThread.class.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Date end = new Date();
		System.out.println("Used time:"
				+ (end.getTime() - start.getTime() + " ms"));
	}
	
	
	public static void notifyThread(Thread t) throws InterruptedException{
		synchronized (TimeCostThread.class) {
			TimeCostThread.class.notify();
		}
		
		t.join();
	}

}
