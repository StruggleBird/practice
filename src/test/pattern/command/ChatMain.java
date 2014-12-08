package test.pattern.command;

/**
 * @author Zhoutao
 * @date 2014年12月8日
 */
public class ChatMain {
	public static void main(String[] args) {
		ChatMediator mediator = new ChatMediatorImpl();
		User user1 = new UserImpl(mediator, "user1");
		User user2 = new UserImpl(mediator, "user2");
		User user3 = new UserImpl(mediator, "user3");
		User user4 = new UserImpl(mediator, "user4");

		mediator.addUser(user1);
		mediator.addUser(user2);
		mediator.addUser(user3);
		mediator.addUser(user4);

		user1.send("Hi all.");
		user2.send("Hi user1!");
	}
}
