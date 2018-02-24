/**
 * 
 */
package org.zt.test.socket;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author Administrator
 * @create 2014-5-15
 */
public class HttpClient {

	String host = "www.baidu.com";
	int port = 80;

	Socket socket;

	public void createSocket() throws UnknownHostException, IOException {
		socket = new Socket(host, port);
	}

	public void communicate() throws IOException {
		String newLine = "\r\n";
		StringBuffer sb = new StringBuffer();
		sb.append("GET /index.php HTTP/1.1").append(newLine);
		sb.append("Host:"+host).append(newLine);
		sb.append("Accept:*/*").append(newLine);
		sb.append("Accept-Language:zh-cn").append(newLine);
		sb.append("Accept-Encoding:gzip,deflate").append(newLine);
		sb.append("User-Agent:Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36 LBBROWSER").append(newLine);
		sb.append("Connection:Keep-Alive").append(newLine+newLine);

		OutputStream os = socket.getOutputStream();
		os.write(sb.toString().getBytes());
		socket.shutdownOutput();

		InputStream is = socket.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		String line = null;
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
		}
		
		
		socket.close();

	}

	/**
	 * @param args
	 * @throws IOException 
	 * @create 2014-5-15
	 */
	public static void main(String[] args) throws IOException {
		HttpClient hc = new HttpClient();
//		hc.host = "";
		System.out.println("请输入一个请求host：");
		BufferedReader inputReader = new LineNumberReader(new InputStreamReader(System.in));
		while ((hc.host = inputReader.readLine())!=null) {
			hc.createSocket();
			hc.communicate();
		}
		
	}

}
