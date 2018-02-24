package org.zt.test.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TalkServer {
	public static final int PORT = 1000;
	
	public BufferedReader getReader(Socket socket) throws IOException{
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
		PrintWriter writer = new PrintWriter(socketOut,true);
		return writer;
	}
	
	public void service(){
		try {
			ServerSocket serverSocket = new ServerSocket(PORT);
			System.out.println("服务器启动");
			Socket socket = serverSocket.accept();
			System.out.println("服务器接收到请求,请求地址  "+socket.getInetAddress()+":"+socket.getPort());
			
			BufferedReader reader = getReader(socket);
			PrintWriter writer = getWriter(socket);

			String message = reader.readLine();

			while (!"bye".equals(message)) {
				System.out.println("accpet request:" + message);
				writer.println("###" + message + "###");
				writer.flush();
				message = reader.readLine();
			}

			reader.close();
			writer.close();
			socket.close();
			serverSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

	public static void main(String[] args) {
		new TalkServer().service();
	}
}
