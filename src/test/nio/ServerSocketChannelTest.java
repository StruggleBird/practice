/**
 * 
 */
package test.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

import org.junit.Test;

/**
 * @author Zhoutao
 * @create 2014-8-30
 */
public class ServerSocketChannelTest {

	@Test
	public void testSocketListen() throws IOException {
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

		serverSocketChannel.socket().bind(new InetSocketAddress(9999));
		// 设置为非阻塞模式  ,疑问：非阻塞模式需要结合select使用才有效果，否则用while循环时cpu暴涨。。
//		serverSocketChannel.configureBlocking(false);
		while (true) {
			SocketChannel socketChannel = serverSocketChannel.accept();

			if (socketChannel != null) {
				System.out.println("获取到链接...");
				ByteBuffer buf = ByteBuffer.allocate(1024);

				int bytesRead = socketChannel.read(buf);
				while (bytesRead != -1) {

					System.out.println("Read " + bytesRead);
					buf.flip();
					while (buf.hasRemaining()) {
						byte[] bytes = new byte[buf.remaining()];
						buf.get(bytes);
						System.out.print(new String(bytes));
					}
					buf.clear();
					bytesRead = socketChannel.read(buf);
					System.out.println("\r\ncapacity:" + buf.capacity() + ",limit:" + buf.limit());
				}
			}else {
				System.out.println("没有获取到链接...");
			}
		}

	}

	@Test
	public void testSocketClient() throws IOException {
		SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(9999));
		ByteBuffer buf = ByteBuffer.allocate(1024);
		buf.put("item.html?name=张三丰".getBytes());
		buf.flip();
		while (buf.hasRemaining()) {
			socketChannel.write(buf);
		}
		socketChannel.close();
	}
}
