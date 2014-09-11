/**
 * 
 */
package test.thread.forkjoin;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ForkJoinPool;

import test.common.TimeCostThread;

/**
 * @author Zhoutao
 * @create 2014年9月7日
 */
public class MainTest {
	public static void main(String[] args) throws IOException,
			InterruptedException {
		 testSingleThread();
//		testMultiThread();
	}

	/**
	 * 
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @create 2014年9月7日
	 */
	private static void testMultiThread() throws IOException, InterruptedException {
		TimeCostThread timeCost = new TimeCostThread();
		timeCost.start();
		countOccurrencesInParallel(Folder.fromDirectory(new File(
				"C:/Users/Administrator/Desktop/docs/lyweb/")), "a");
		TimeCostThread.notifyThread(timeCost);

	}

	final static ForkJoinPool forkJoinPool = new ForkJoinPool();

	static Long countOccurrencesInParallel(Folder folder, String searchedWord) {
		return forkJoinPool.invoke(new FolderSearchTask(folder, searchedWord));
	}

	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * @create 2014年9月7日
	 */
	private static void testSingleThread() throws IOException,
			InterruptedException {
		WordCounter wordCounter = new WordCounter();
		TimeCostThread timeCost = new TimeCostThread();
		timeCost.start();
		Folder folder = Folder.fromDirectory(new File(
				"C:/Users/Administrator/Desktop/docs/lyweb/"));

		System.out.println(wordCounter.countOccurrencesOnSingleThread(folder,
				"a"));

		TimeCostThread.notifyThread(timeCost);
	}
}
