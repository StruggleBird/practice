/**
 * 
 */
package test.socket.mail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import sun.misc.BASE64Encoder;

/**
 * @author Zhoutao
 * @create 2014-6-10
 */
public class MailServerWithAuth {
	private String smtpServer = "smtp.126.com";
	private int port = 25;

	public static void main(String[] args) {
		Message msg = new Message("mjhx_zt@126.com", "mjhx_zt@qq.com", "just test", "I miss you!");
		new MailServerWithAuth().sendMail(msg);
	}

	public BufferedReader getReader(Socket socket) throws IOException {
		InputStream socketIn = socket.getInputStream();
		return new BufferedReader(new InputStreamReader(socketIn));
	}

	/**
	 * @param socket
	 * @return
	 * @throws IOException
	 * @create 2014-5-13
	 */
	private PrintWriter getWriter(Socket socket) throws IOException {
		OutputStream socketOut = socket.getOutputStream();
		PrintWriter writer = new PrintWriter(socketOut, true);
		return writer;
	}

	public void sendMail(Message message) {
		Socket socket = null;

		try {
			socket = new Socket(smtpServer, 25);
			BufferedReader reader = getReader(socket);
			PrintWriter writer = getWriter(socket);
			String localhost = InetAddress.getLocalHost().getHostName();
			String username = new BASE64Encoder().encode("mjhx_zt".getBytes());
			String password = new BASE64Encoder().encode("wangji".getBytes());

			sendAndReceive(null, reader, writer);
			sendAndReceive("HELO " + localhost, reader, writer);
			sendAndReceive("AUTH LOGIN", reader, writer);
			sendAndReceive(username, reader, writer);
			sendAndReceive(password, reader, writer);

			sendAndReceive("MAIL FROM:<" + message.from + ">", reader, writer);
			sendAndReceive("RCPT TO:<" + message.to + ">", reader, writer);
			sendAndReceive("DATA", reader, writer);
			sendAndReceive(message.data, reader, writer);
//			writer.println(message.data);
//			System.out.println("Client>"+message.data);
//			sendAndReceive(".", reader, writer);
			sendAndReceive("QUIT", reader, writer);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (socket != null) {
					socket.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	private void sendAndReceive(String str, BufferedReader reader, PrintWriter writer) throws IOException {
		if (str != null) {
			System.out.println("Client>" + str);
			writer.println(str);
		}

		String response;
		if ((response = reader.readLine()) != null) {
			System.out.println("Server>" + response);
		}

	}
}
