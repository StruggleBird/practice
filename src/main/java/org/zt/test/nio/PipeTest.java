/**
 * 
 */
package org.zt.test.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

import org.junit.Test;

/**
 * @author Zhoutao
 * @create 2014-8-31
 */
public class PipeTest {

	/**
	 * 要向管道写数据，需要访问sink通道。
	 * 通过调用SinkChannel的write()方法，将数据写入SinkChannel
	 * @throws IOException
	 * @create 2014-8-31
	 */
	@Test
	public void testPipe() throws IOException {
		Pipe pipe = Pipe.open();
		//写入数据到管道
		Pipe.SinkChannel sinkChannel = pipe.sink();
		ByteBuffer buf = ByteBuffer.allocate(1024);
		buf.put("abc你".getBytes());
		buf.flip();
		while (buf.hasRemaining()) {
			sinkChannel.write(buf);
		}

		//读取数据从管道
		Pipe.SourceChannel sourceChannel = pipe.source();
		ByteBuffer bufRead = ByteBuffer.allocate(1024);
		sourceChannel.read(bufRead);
		bufRead.flip();
		while (bufRead.hasRemaining()) {
			byte[] bytes = new byte[bufRead.remaining()];
			bufRead.get(bytes);
			System.out.println(new String(bytes));
		}
		

	}

}
