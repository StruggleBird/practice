package org.zt.test.thread.concurrent;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class DelayQueueTest {

	/**
	 * 使用DelayQueue实现一个简易的调度系统
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void testSchedule() throws InterruptedException {
		DelayQueue<Task> delayQueue = new DelayQueue<>();
		long begin = System.currentTimeMillis();
		System.out.println("简易任务调度系统：");
		delayQueue.put(new Task("task1", begin + 2000)); // 加入任务1
		delayQueue.put(new Task("task2", begin + 2000)); // 加入任务2
		delayQueue.put(new Task("task3", begin + 5000)); // 加入任务3
		while (true) {
			Task task = delayQueue.take();
			System.out.println("任务: " + task.getName() + " 启动,等待：" + (System.currentTimeMillis() - begin));
		}
	}

	private class Task implements Delayed {

		private long startTime;
		private String name;

		public Task(String name, long startTime) {
			this.startTime = startTime;
			this.name = name;
		}

		private long now() {
			return System.currentTimeMillis();
		}

		public int compareTo(Delayed o) {
			Task task2 = (Task) o;
			return (int) (this.startTime - task2.startTime);
		}

		@Override
		public long getDelay(TimeUnit unit) {
			return unit.convert(startTime - now(), TimeUnit.NANOSECONDS);
		}

		public long getStartTime() {
			return startTime;
		}

		public String getName() {
			return name;
		}

	}
}
