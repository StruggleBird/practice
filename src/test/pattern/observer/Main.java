/**
 * 
 */
package test.pattern.observer;

/**
 * @author Zhoutao
 * @create 2014-8-26
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {
		Subject subject = new HeadHunter();
		subject.registerObserver(new JobSeeker("zhoutao"));
		subject.registerObserver(new JobSeeker("taolei"));

		subject.addJob(new Job("java软件工程师", "soft", "5"));

		Thread.sleep(3000);
		subject.addJob(new Job("架构师", "soft", "7"));
	}
}
