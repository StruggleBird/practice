/**
 * 
 */
package org.zt.test.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;

/**
 * @author Zhoutao
 * @create 2014-8-31
 */
public class SelectorTest {
	private static int port = 9998;
	@Test
	public void testSelector() throws IOException {
		Selector selector = Selector.open();
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

		serverSocketChannel.socket().bind(new InetSocketAddress(port));
		// 设置为非阻塞模式  
		serverSocketChannel.configureBlocking(false);
		SelectionKey key = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

		while (true) {
			System.out.println("打开Selector..");
			int channelCount = selector.select();
			System.out.println("获取到准备就绪的通道数:" + channelCount);
			if (channelCount == 0)
				continue;

			Set<SelectionKey> selectedKeys = selector.selectedKeys();
			Iterator<SelectionKey> keyIt = selectedKeys.iterator();
			while (keyIt.hasNext()) {
				System.out.println("剩余数量：" + selectedKeys.size());
				key = keyIt.next();
				if (key.isAcceptable()) {
					System.out.println("开始接收数据...");
					ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
					System.out.println(serverChannel);
				} else if (key.isReadable()) {
					System.out.println("可读。。");
				}
				keyIt.remove();
			}
		}

	}

	@Test
	public void testSelector2() throws IOException {
		ServerSocketChannel channel = ServerSocketChannel.open();

		channel.socket().bind(new InetSocketAddress(port));
		Selector selector = Selector.open();

		channel.configureBlocking(false);

		channel.register(selector, SelectionKey.OP_ACCEPT);

		while (true) {

			int readyChannels = selector.select();

			if (readyChannels == 0)
				continue;

			Set<SelectionKey> selectedKeys = selector.selectedKeys();

			Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

			while (keyIterator.hasNext()) {

				SelectionKey key = keyIterator.next();

				if (key.isAcceptable()) {
					System.out.println("accept...");
					// a connection was accepted by a ServerSocketChannel.

				} else if (key.isConnectable()) {
					// a connection was established with a remote server.

				} else if (key.isReadable()) {
					// a channel is ready for reading

				} else if (key.isWritable()) {
					// a channel is ready for writing
				}

				keyIterator.remove();
			}
		}
	}

	@Test
	public void testSocketClient() throws IOException {
		SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(port));
		ByteBuffer buf = ByteBuffer.allocate(1024);
		buf.put("item.html?name=张三丰".getBytes());
		buf.flip();
		while (buf.hasRemaining()) {
			socketChannel.write(buf);
		}
		socketChannel.close();
	}

	/**
	 * 移位运算
	 * 
	 * @create 2014-8-31
	 */
	@Test
	public void testSelectorKey() {
		System.out.println(SelectionKey.OP_ACCEPT);
		System.out.println(SelectionKey.OP_READ);
		System.out.println(SelectionKey.OP_READ | SelectionKey.OP_ACCEPT);
	}
}
