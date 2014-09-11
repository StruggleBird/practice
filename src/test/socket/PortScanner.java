/**
 * 
 */
package test.socket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

/**
 * @author Administrator
 * @create 2014-5-14
 */
public class PortScanner {

	/**
	 * @param args
	 * @throws UnknownHostException 
	 * @create 2014-5-14
	 */
	public static void main(String[] args) throws UnknownHostException {
		scan("www.baidu.com");
		
		System.out.println(InetAddress.getLocalHost().getHostAddress());
		System.out.println(InetAddress.getByName("www.baiu.com").getHostAddress());
		System.out.println(InetAddress.getByName("127.0.0.1").getHostAddress());
		System.out.println(InetAddress.getByName("192.196.1.103").getHostAddress());
	}
	
	public static void scan(String host) {
		for (int i = 70; i < 100; i++) {
			int port = i;
			try {
				System.out.println("scanning port "+i);
				Socket socket = new Socket();
				SocketAddress address = new InetSocketAddress(host, port);
				socket.connect(address, 200);
				System.out.println("There is a server on port "+i);
				
				
			} catch (UnknownHostException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

}
