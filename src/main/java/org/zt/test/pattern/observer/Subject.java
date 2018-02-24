/**
 * 
 */
package org.zt.test.pattern.observer;

import java.util.List;

/**
 * 主题，负责注册订阅者，通知订阅者，添加job
 * 观察者模式=发布者+订阅者
 * 观察者模式区别于生产消费模式：观察者模式发布者和订阅者紧耦合，数据/消息存储在发布方，需要针对每个订阅者发送定制信息；
 * 生产者消费者模式：两者松耦合，生产者只需生产数据/消息，其他的无需关注。消费者只需要处理自己需要处理的消息，无需关注消息来源
 * @author Zhoutao
 * @create 2014-8-26
 */
public interface Subject {
	public void registerObserver(Observer observer);

	public void removeObserver(Observer observer);

	public void notifyAllObservers();

	public void addJob(Job job);

	public List<Job> getJobs();

}
