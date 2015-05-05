package test.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import junit.framework.TestCase;

public class SocketClientTest extends TestCase {


    public void testConnBaidu() throws UnknownHostException, IOException {

        SocketAddress remote = new InetSocketAddress("www.baidu.com", 80);

        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        // channel.socket().bind(remote);


        Selector selector = Selector.open();
        channel.connect(remote);
        channel.register(selector, SelectionKey.OP_CONNECT);

        while (true) {
            selector.select();

            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

            while (iterator.hasNext()) {
                SelectionKey key = (SelectionKey) iterator.next();
                iterator.remove();

                if (key.isConnectable()) {
                    SocketChannel sockChannel = (SocketChannel) key.channel();
                    sockChannel.configureBlocking(false);

                    if (sockChannel.isConnectionPending()) {
                        sockChannel.finishConnect();
                    }

                    ByteBuffer byteBuf = ByteBuffer.wrap("hello".getBytes());
                    sockChannel.write(byteBuf);
                    channel.register(selector, SelectionKey.OP_READ);
                    System.out.println("客户端连接成功");
                } else if (key.isReadable()) {
                    SocketChannel sockChannel = (SocketChannel) key.channel();
                    ByteBuffer buf = ByteBuffer.allocate(1024);
                    sockChannel.read(buf);

                    byte[] data = buf.array();
                    System.out.println("response:" + new String(data));
                }
            }
        }
    }

}
