/**
 * 
 */
package org.zt.test.nio.timedemo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Ternence
 * @create 2015年5月1日
 */
public class MultiPlexerTimerServer implements Runnable {

    private Selector selector;
    private ServerSocketChannel srvChannel;
    private volatile boolean stop;

    private Map<SocketChannel, byte[]> clientMessage =
            new ConcurrentHashMap<SocketChannel, byte[]>();

    /**
     * 
     */
    public MultiPlexerTimerServer(int port) {
        try {
            selector = Selector.open();
            srvChannel = ServerSocketChannel.open();
            srvChannel.configureBlocking(false);
            srvChannel.bind(new InetSocketAddress(port));
            srvChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("Server socket channel started.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        this.stop = true;
    }


    @Override
    public void run() {
        while (!stop) {
            try {
                int num = selector.select();
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> itKeys = selectedKeys.iterator();
                while (itKeys.hasNext()) {
                    SelectionKey key = itKeys.next();
                    itKeys.remove();
                    try {
                        handleInput(key);
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (key != null) {
                            key.cancel();
                            if (key.channel() != null) {
                                key.channel().close();
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }

    /**
     * @param key
     * @throws IOException
     * @create 2015年5月1日
     */
    private void handleInput(SelectionKey key) throws IOException {
        if (!key.isValid()) {
            System.out.println("Invalid selectionKey!");
            return;
        }

        if (key.isAcceptable()) {
            ServerSocketChannel srvChannel = (ServerSocketChannel) key.channel();
            SocketChannel channel = srvChannel.accept();
            channel.configureBlocking(false);
            channel.register(selector, SelectionKey.OP_READ);
            System.out.println("New client accepted.");
        } else if (key.isReadable()) {
            read(key);
        }

    }

    /**
     * @param key
     * @throws IOException
     * @throws UnsupportedEncodingException
     * @create 2015年5月1日
     */
    private void read(SelectionKey key) throws IOException, UnsupportedEncodingException {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer readBuffer = ByteBuffer.allocate(5);
        int readBytes = channel.read(readBuffer);

        if (readBytes > 0) {
            readBuffer.flip();

            byte[] bytes = clientMessage.get(channel);
            if (bytes == null) {
                bytes = new byte[readBytes];
                readBuffer.get(bytes);
            } else {
                byte[] bytes2 = new byte[readBytes];
                readBuffer.get(bytes2);
                bytes = merge(bytes, bytes2);
            }
            clientMessage.put(channel, bytes);
            
            String msg = new String(bytes, "UTF-8");
            System.out.println("The time server received order:" + new String(clientMessage.get(channel),"UTF-8"));

            String response = new Date().toGMTString();
             doWrite(channel, response);
        } 
    }

    /**
     * @param bytes
     * @param bytes2
     * @return
     * @create 2015年5月1日
     */
    private byte[] merge(byte[] bytes, byte[] bytes2) {
        byte[] newBytes = new byte[bytes.length + bytes2.length];
        System.arraycopy(bytes, 0, newBytes, 0, bytes.length);
        System.arraycopy(bytes2, 0, newBytes, bytes.length, bytes2.length);
        return newBytes;
    }

    /**
     * @param channel
     * @param response
     * @throws IOException
     * @create 2015年5月1日
     */
    private void doWrite(SocketChannel channel, String response) throws IOException {
        if (response == null && response.length() == 0) {
            return;
        }
        byte[] bytes = response.getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
        writeBuffer.put(bytes);
        writeBuffer.flip();
        channel.write(writeBuffer);
    }

}
