/**
 * 
 */
package org.zt.test.pattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zhoutao
 * @create 2014-8-26
 */
public class HeadHunter implements Subject {

	private List<Observer> observers = new ArrayList<Observer>();
	private List<Job> jobs = new ArrayList<Job>();

	@Override
	public void registerObserver(Observer observer) {
		if (!observers.contains(observer)) {
			observers.add(observer);
		}
	}

	@Override
	public void removeObserver(Observer observer) {
		if (observers.contains(observer)) {
			observers.remove(observer);
		}
	}

	@Override
	public void notifyAllObservers() {
		for (Observer observer : observers) {
			observer.update(this);
		}
	}
	
	public void addJob(Job job){
		this.jobs.add(job);
		notifyAllObservers();
	}
	
	public List<Job> getJobs(){
		return this.jobs;
	}

	public List<Observer> getObservers() {
		return observers;
	}

	public void setObservers(List<Observer> observers) {
		this.observers = observers;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	

}
