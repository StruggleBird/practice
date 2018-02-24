/**
 * 
 */
package org.zt.test.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.junit.Test;

/**
 * @author Zhoutao
 * @create 2014-8-30
 */
public class FileChannelTest {

	@Test
	public void testRead() throws IOException {
		RandomAccessFile aFile = new RandomAccessFile("a.txt", "rw");
		FileChannel inChannel = aFile.getChannel();

		ByteBuffer buf = ByteBuffer.allocate(20);
		int bytesRead = inChannel.read(buf);
		while (bytesRead != -1) {

			System.out.println("Read " + bytesRead);
			buf.flip();
			while (buf.hasRemaining()) {
				System.out.print((char) buf.get());
			}
			//			buf.clear();
			buf.compact();
			bytesRead = inChannel.read(buf);
			System.out.println("\r\ncapacity:" + buf.capacity() + ",limit:" + buf.limit());
		}
		aFile.close();
	}

	/**
	 * 从其他通道传输字节到当前通道
	 * @throws IOException
	 * @create 2014-8-31
	 */
	@Test
	public void testTransferFrom() throws IOException {
		RandomAccessFile fromFile = new RandomAccessFile("1.txt", "rw");
		FileChannel fromChannel = fromFile.getChannel();

		RandomAccessFile toFile = new RandomAccessFile("2.txt", "rw");
		FileChannel toChannel = toFile.getChannel();

		long position = 0;
		long count = fromChannel.size();

		toChannel.transferFrom(fromChannel, position, count);
		System.out.println("从文件1.txt copy 内容到2.txt 成功！");
	}

	@Test
	public void testTransferTo() throws IOException {
		String mode = "rw";
		RandomAccessFile fromFile = new RandomAccessFile("2.txt", mode);
		FileChannel fromChannel = fromFile.getChannel();
		RandomAccessFile toFile = new RandomAccessFile("1.txt", mode);
		FileChannel toChannel = toFile.getChannel();
		fromChannel.transferTo(fromChannel.position(), fromChannel.size(), toChannel);
	}
}
