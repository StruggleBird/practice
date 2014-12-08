package test.pattern.command;

/**
 * 首先创建调解人接口，用于如何定义具体的调解人。
 * 
 * @author Zhoutao
 * @date 2014年12月8日
 */
public interface ChatMediator {

	void sendMessage(String message, User user);

	void addUser(User user);
}
