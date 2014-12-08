package test.pattern.command;

import java.util.ArrayList;
import java.util.List;

/**
 * 现在调解人的实体类，它包含了再组群中的一些类用户并且提供用户之间如何交流的逻辑
 * 
 * @author Zhoutao
 * @date 2014年12月8日
 */
public class ChatMediatorImpl implements ChatMediator {

	private List<User> users;

	/**
	 * 
	 */
	public ChatMediatorImpl() {
		users = new ArrayList<User>();
	}

	@Override
	public void sendMessage(String message, User user) {
		for (User u : users) {
			if (u != user) {
				u.receive(message);
			}
		}
	}

	@Override
	public void addUser(User user) {
		users.add(user);
	}

}
