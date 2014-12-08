package test.pattern.command;

/**
 * 现在创建用户的实体类用于客户端中
 * 注意，send()方法正在调解人中被用于传递信息给其他用户。但是它不知道如何被此调解人使用。
 * 
 * @author Zhoutao
 * @date 2014年12月8日
 */
public class UserImpl extends User {

	/**
	 * @param chatMediator
	 * @param name
	 */
	public UserImpl(ChatMediator chatMediator, String name) {
		super(chatMediator, name);
	}

	@Override
	public void send(String message) {
		System.out.println(this.name + ": Sending message: " + message);
		chatMediator.sendMessage(message, this);
	}

	@Override
	public void receive(String message) {
		System.out.println(this.name + ": Receiving message: " + message);
	}

}
