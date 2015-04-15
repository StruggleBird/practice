package test.nio.socketdemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * User: hAdIx Date: 11-11-2 Time: 上午11:26
 */
public class Client {

    public void start() throws IOException {
        SocketChannel sc = SocketChannel.open();
        sc.configureBlocking(false);
        sc.connect(new InetSocketAddress("localhost", 8001));
        Selector selector = Selector.open();
        sc.register(selector, SelectionKey.OP_CONNECT);
        Scanner scanner = new Scanner(System.in);
        byte[] response = new byte[0];
        while (true) {
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            System.out.println("key size=" + keys.size());
            Iterator<SelectionKey> keyIterator = keys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                keyIterator.remove();
                if (key.isConnectable()) {
                    sc.finishConnect();
                    sc.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ);
                    System.out.println("server connected...");
                    break;
                } else if (key.isReadable()) {
                    ByteBuffer dst = ByteBuffer.allocate(10);
                    int bytes = sc.read(dst);
                    if (bytes > 0) {
                        byte[] readBytes = dst.array();
                        byte[] result = new byte[bytes + response.length];
                        System.arraycopy(response, 0, result, 0, response.length);
                        System.arraycopy(readBytes, 0, result, response.length, bytes);
                        response = result;
                        System.out.println("Response:" + new String(readBytes));
                    }
                } else if (key.isWritable()) {

                    System.out.println("Please input message");
                    String message = scanner.nextLine();
                    ByteBuffer writeBuffer = ByteBuffer.wrap(message.getBytes());
                    sc.write(writeBuffer);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Client().start();
    }
}
