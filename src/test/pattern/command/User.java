package test.pattern.command;

/**
 * 用户可以发送接送信息，因此应该有用户接口或者抽象类。此处创建用户抽象类如下
 * 
 * @author Zhoutao
 * @date 2014年12月8日
 */
public abstract class User {
	protected ChatMediator chatMediator;
	protected String name;

	/**
	 * @param chatMediator
	 * @param name
	 */
	public User(ChatMediator chatMediator, String name) {
		super();
		this.chatMediator = chatMediator;
		this.name = name;
	}

	public abstract void send(String message);

	public abstract void receive(String message);
}
