package org.zt.test.socket.mail;

public class Message {
	String from;
	String to;
	String subject;
	String content;
	String data;

	/**
	 * @param from
	 * @param to
	 * @param subject
	 * @param content
	 * @param data
	 */
	public Message(String from, String to, String subject, String content) {
		super();
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.content = content;
		this.data = "Subject:" + subject + "\r\n" + content+"\r\n.";
	}

}