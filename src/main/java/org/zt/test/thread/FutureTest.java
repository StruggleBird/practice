package org.zt.test.thread;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * 总共命中多少次，future应用 所有线程执行完某个操作，然后再执行一个操作
 * 
 * @author User
 *
 */
public class FutureTest {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter base directory (e.g. /Users/User/Downloads/src):");
		String directory = in.nextLine();
		if (directory == null || directory.length() == 0) {
			directory = "/Users/User/Downloads/src";
			System.out.println("directory is null,set default is /Users/User/Downloads/src.");
		}
		System.out.print("Enter keyword (e.g. volatile):");
		String keyword = in.nextLine();
		if (keyword == null || keyword.length() == 0) {
			keyword = "volatile";
			System.out.println("keyword is null,set default volatile.");
		}
		long start = System.currentTimeMillis();
		MatchCounter counter = new MatchCounter(new File(directory), keyword);
		FutureTask<Integer> task = new FutureTask<Integer>(counter);
		Thread t = new Thread(task);
		t.start();
		try {
			System.out.println(task.get() + " matching files.");
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {

		}
		System.out.println("use " + (System.currentTimeMillis() - start) + " ms");
	}

}

class MatchCounter implements Callable<Integer> {

	public MatchCounter(File directory, String keyword) {
		this.directory = directory;
		this.keyword = keyword;
	}

	public Integer call() {
		count = 0;
		try {
			File[] files = directory.listFiles();
			ArrayList<Future<Integer>> results = new ArrayList<Future<Integer>>();
			for (File file : files) {
				if (file.isDirectory()) {
					MatchCounter counter = new MatchCounter(file, keyword);
					FutureTask<Integer> task = new FutureTask<Integer>(counter);
					results.add(task);
					Thread t = new Thread(task);
					t.start();
				} else {
					if (search(file))
						count++;
				}
			}

			for (Future<Integer> result : results) {
				try {
					count += result.get();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}
		} catch (InterruptedException e) {

		}
		return count;
	}

	public boolean search(File file) {
		try {
			Scanner in = new Scanner(new FileInputStream(file));
			boolean found = false;
			while (!found && in.hasNextLine()) {
				String line = in.nextLine();
				if (line.contains(keyword))
					found = true;
			}
			in.close();
			return found;
		} catch (IOException e) {
			return false;
		}
	}

	private File directory;
	private String keyword;
	private int count;
}
