package org.zt.test.concurrent.lock;

import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Mutex implements Lock {

	// 静态内部类 ，自定义同步器
	private static class Sync extends AbstractQueuedSynchronizer {

		// 是否处于占用状态
		@Override
		protected boolean isHeldExclusively() {
			return getState() == 1;
		}

		/**
		 * 当状态为零的时候获取锁.
		 * 公平锁和非公平锁的主要区别在于是否判断已经有队列在等待获取锁。
		 * 公平锁：判断如果有队列在前面，则返回失败，最终会将当前线程加入到队列末尾
		 * 非公平锁：不断的尝试设置状态知道成功为止，没有排队的机制，该方法永远返回true
		 */
		@Override
		protected boolean tryAcquire(int arg) {
			if (!hasQueuedPredecessors() && compareAndSetState(0, 1)) {
				setExclusiveOwnerThread(Thread.currentThread());
				return true;
			}
			return false;
		}

		@Override
		protected boolean tryRelease(int arg) {
			if (getState() == 0) {
				throw new IllegalMonitorStateException();
			}
			setExclusiveOwnerThread(null);
			setState(0);
			return true;

		}

		/**
		 * 返回一個Condition ，每个Condition都包含一个Condition队列
		 * 
		 * @return
		 */
		Condition newCondition() {
			return new ConditionObject();
		}

	}

	private final Sync sync = new Sync();

	@Override
	public void lock() {
		sync.acquire(1);
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		sync.acquireInterruptibly(1);
	}

	@Override
	public boolean tryLock() {
		return sync.tryAcquire(1);
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		return sync.tryAcquireNanos(1, unit.toNanos(time));
	}

	@Override
	public void unlock() {
		sync.release(1);
	}

	@Override
	public Condition newCondition() {
		return sync.newCondition();
	}

	public boolean isLocked() {
		return sync.isHeldExclusively();
	}

	public static void main(String[] args) throws InterruptedException {

		ExecutorService threadPool = Executors.newFixedThreadPool(10);
		Mutex mutex = new Mutex();
		for (int i = 0; i < 10; i++) {
			threadPool.submit(new Runnable() {
				@Override
				public void run() {
					while (true) {
						mutex.lock();
						try {
							System.out.println("Thread " + Thread.currentThread().getName() + ",进入代码块");
							Thread.sleep(1000);
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							mutex.unlock();
						}
					}
				}
			});
		}
		Thread.sleep(1000);
		while (true) {
			System.out.print("排队等待的线程：");
			Collection<Thread> queuedThreads = mutex.sync.getQueuedThreads();
			Thread lastThread = null;
			for (Thread thread : queuedThreads) {
				System.out.print(thread.getName() + "->");
				lastThread = thread;
			}

			System.out.println(lastThread.getState());
			Thread.sleep(1000);
		}

		// Thread.currentThread().join();

	}

}
