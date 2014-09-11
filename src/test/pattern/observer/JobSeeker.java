/**
 * 
 */
package test.pattern.observer;

/**
 * @author Zhoutao
 * @create 2014-8-26
 */
public class JobSeeker implements Observer{

	private String name ;
	
	/**
	 * @param name
	 */
	public JobSeeker(String name) {
		super();
		this.name = name;
	}



	@Override
	public void update(Subject subject) {
		System.out.println(this.name + " got notified!");
		System.out.println(subject.getJobs());
	}

}
