/**
 * 
 */
package org.zt.test.thread.lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Ternence
 * @create 2015年1月29日
 */
public class ReadWriteLockTest {
	public static void main(String[] args) {
		PricesInfo pricesInfo = new PricesInfo();
		Reader[] readers = new Reader[5];
		Thread[] readThread = new Thread[5];
		for (int i = 0; i < 5; i++) {
			readers[i] = new Reader(pricesInfo);
			readThread[i] = new Thread(readers[i]);
			readThread[i].start();
		}

		Writer writer = new Writer(pricesInfo);
		Thread writerThread = new Thread(writer);
		writerThread.start();

	}
}

class PricesInfo {

	private double price1;
	private double price2;
	private ReadWriteLock lock;

	/**
	 * 
	 */
	public PricesInfo() {
		price1 = 1.0;
		price2 = 2.0;
		lock = new ReentrantReadWriteLock();
	}

	/**
	 * @return the price1
	 */
	public double getPrice1() {
		lock.readLock().lock();
		double value = price1;
		lock.readLock().unlock();
		return value;
	}

	/**
	 * @param price1
	 *            the price1 to set
	 */
	public void setPrice(double price1, double price2) {
		lock.writeLock().lock();
		System.out.printf("Writer: Attempt to modify the prices.\n");
		this.price1 = price1;
		this.price2 = price2;
		System.out.printf("Writer: Prices have been modified.\n");
		lock.writeLock().unlock();
	}

	/**
	 * @return the price2
	 */
	public double getPrice2() {
		lock.readLock().lock();
		double value = price2;
		lock.readLock().unlock();

		return value;
	}

}

class Reader implements Runnable {

	private PricesInfo pricesInfo;

	/**
	 * 
	 */
	public Reader(PricesInfo pricesInfo) {
		this.pricesInfo = pricesInfo;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.printf("%s: Price 1: %f\n", Thread.currentThread()
					.getName(), pricesInfo.getPrice1());
			System.out.printf("%s: Price 2: %f\n", Thread.currentThread()
					.getName(), pricesInfo.getPrice2());
		}
	}

}

class Writer implements Runnable {
	private PricesInfo pricesInfo;

	public Writer(PricesInfo pricesInfo) {
		this.pricesInfo = pricesInfo;
	}

	public void run() {
		for (int i = 0; i < 3; i++) {

			pricesInfo.setPrice(Math.random() * 10, Math.random() * 8);
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}