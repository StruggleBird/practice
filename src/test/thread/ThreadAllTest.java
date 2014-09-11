/**
 * 
 */
package test.thread;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import junit.framework.TestCase;

import org.apache.commons.io.FileUtils;

/**
 * @author Zhoutao
 * @create 2014-8-19
 */
public class ThreadAllTest extends TestCase {

	static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();

	public void testThreadLocal() throws InterruptedException {

		threadLocal.set(1);

		innerMethod1();
		System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
		Thread.sleep(500);
	}

	/**
	 * 
	 * @create 2014-8-19
	 */
	private void innerMethod1() {
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
				threadLocal.set(2);
				System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
			}
		});
		t1.setName("innerThread");
		t1.start();
	}

	/**
	 * 主线程等待子线程唤醒
	 * @throws InterruptedException
	 * @create 2014-8-23
	 */
	public void testWaitNotify() throws InterruptedException {
		innerThread2(Thread.currentThread());
		System.out.println("end");
	}

	/**
	 * 主线程和t2线程等待t1线程唤醒
	 * @throws InterruptedException
	 * @create 2014-8-23
	 */
	public void testWaitNotify2() throws InterruptedException {
		final Object lock = new Object();

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("t1 running.");

				synchronized (lock) {
					lock.notifyAll();
					System.out.println("t1 notifyAll.");
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("t2 running");
				synchronized (lock) {
					try {
						System.out.println("t2 watting");
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("t2 has bean actived.");
				}
			}
		});

		synchronized (lock) {
			t1.start();
			t2.start();
			System.out.println("Main thread waitting");
			lock.wait();
			System.out.println("Main thread has bean actived.");
		}
		Thread.sleep(2000);// 等待所有程序结束
		System.out.println("Program end.");
	}

	/**
	 * 
	 * @throws InterruptedException 
	 * @create 2014-8-19
	 */
	private void innerThread2(final Thread mainThread) throws InterruptedException {
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					System.out.println("inner thread start.");
					Thread.sleep(3000);
					synchronized (threadLocal) {
						threadLocal.notify();//唤醒等待threadLocal监控器的单个线程. 
						System.out.println("等待结束，重新唤醒");
					}
					System.out.println("inner thread  end.");

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		t1.setName("innerThread");
		synchronized (threadLocal) {
			t1.start();
			threadLocal.wait(); //释放threadLocal对象锁，同时释放cpu资源。当前线程等待被唤醒
			System.out.println("main thread end");
			//			System.out.println("main before wait," + t1.getState());
			//			t1.wait();
			//			System.out.println("main end wait," + t1.getState());
			//			Thread.sleep(3000);
			//			
			//			t1.notify();
		}

	}

	/**
	 * countDownLatch 测试
	 * @throws InterruptedException
	 * @create 2014-8-24
	 */
	public void testCountDownLatch() throws InterruptedException {
		final CountDownLatch countDownLatch = new CountDownLatch(2);
		final List<String> contents;
		contents = new ArrayList<String>();
		new Thread(new Runnable() {

			@Override
			public void run() {
				File file = new File("1.txt");
				String content = null;
				try {
					content = FileUtils.readFileToString(file);
				} catch (IOException e) {
					e.printStackTrace();
				}
				contents.add(content);
				countDownLatch.countDown();
			}
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				File file = new File("2.txt");
				String content = null;
				try {
					content = FileUtils.readFileToString(file);
				} catch (IOException e) {
					e.printStackTrace();
				}
				contents.add(content);
				countDownLatch.countDown();
			}
		}).start();

		countDownLatch.await();
		System.out.println(contents);
		System.out.println("All done.");
	}

	static volatile int volatileCount = 0;

	//	static  int volatileCount = 0;

	public void testVolatile() throws InterruptedException {
		//		List<Thread> threadList = new ArrayList<Thread>();
		for (int i = 0; i < 1000; i++) {
			Thread t1 = new Thread(new Runnable() {
				@Override
				public void run() {
					atomicAdd();
				}

				public void atomicAdd() {

					volatileCount++;
					System.out.println("thread:" + Thread.currentThread().getName());
				}
			});
			t1.setName(i + "");
			t1.start();
		}

		Thread.sleep(5000);

		System.out.println("total count:" + volatileCount);
		System.out.println("end");
	}


	public void testFuture() throws InterruptedException, ExecutionException, TimeoutException {
		ExecutorService service = Executors.newCachedThreadPool();

		FutureTask<String> task = new FutureTask<String>(new Callable<String>() {

			@Override
			public String call() throws Exception {
				return "abc";
			}
		});
		service.execute(task);
		System.out.println(task.get(1000, TimeUnit.MILLISECONDS));
	}
}
