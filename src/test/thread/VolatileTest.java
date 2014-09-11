/**
 * 
 */
package test.thread;

/**
 * @author Zhoutao
 * @create 2013-12-8
 */
public class VolatileTest {

	public static volatile int race = 0;
	
	public static  void increase()
	{
		race++;
	}
	
	private static final int THREAD_COUNT = 20;
	
	/**
	 * @param args
	 * @throws InterruptedException 
	 * @create 2013-12-8
	 */
	public static void main(String[] args) throws InterruptedException {
		Thread[] threads = new Thread[20];
		for (int i = 0; i < THREAD_COUNT; i++) {
			threads[i] = new Thread(new Runnable() {
				
				@Override
				public void run() {
					for (int j = 0; j < 10000; j++) {
						increase();
					}
				}
			});
			
			threads[i].start();
		}
		
//		while (Thread.activeCount() > 1) {
//			Thread.yield();
//			System.out.println(Thread.activeCount());
//		}
//		Thread.currentThread().
		threads[19].join();
		System.out.println(Thread.activeCount());
		System.out.println(race);
		
	}

}
