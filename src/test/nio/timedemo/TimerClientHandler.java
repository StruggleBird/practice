/**
 * 
 */
package test.nio.timedemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Ternence
 * @create 2015年5月1日
 */
public class TimerClientHandler implements Runnable{

    private SocketChannel channel;
    private Selector selector;
    private int port;
    private volatile boolean stop;
    
    /**
     * 
     */
    public TimerClientHandler(int port) {
        this.port = port;
        
    }

    @Override
    public void run() {
        doConnection();
        while (!stop) {
            try {
                int num = selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> itKeys = keys.iterator();
                while (itKeys.hasNext()) {
                    SelectionKey key = (SelectionKey) itKeys.next();
                    itKeys.remove();
                    try {
                        handleInput(key);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        if (key != null) {
                            key.cancel();
                        }
                    }
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
        if (selector!= null) {
            try {
                selector.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (channel!= null&& channel.isOpen()) {
            try {
                channel.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
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
            System.out.println("Invalid selectionKey");
            return;
        }
        SocketChannel channel =  (SocketChannel) key.channel();
        
        if (key.isReadable()) {
            
            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
            int readbytes = channel.read(readBuffer );
            if (readbytes > 0) {
                readBuffer.flip();
                byte[] bytes = new byte[readbytes];
                readBuffer.get(bytes);
                String response = new String(bytes,"UTF-8");
                System.out.println("Client received response:"+response);
                stop = true;
            }
        }else if(key.isConnectable()){
            if (channel.finishConnect()) {
                channel.register(selector, SelectionKey.OP_READ);
                System.out.println("Finished connection");
                doWrite(channel);
            }
        }
            
    }

    /**
     * 
     * @create 2015年5月1日
     */
    private void doConnection() {
        try {
            selector  = Selector.open();
            channel = SocketChannel.open();
            channel.configureBlocking(false);
            boolean established = channel.connect(new InetSocketAddress(port));
            if (established) {                
                channel.register(selector, SelectionKey.OP_CONNECT);
                System.out.println("Try to connect to server.");
                doWrite(channel);
            }else {
                channel.register(selector, SelectionKey.OP_CONNECT);
            }
        } catch (ClosedChannelException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param channel2
     * @throws IOException 
     * @create 2015年5月1日
     */
    private void doWrite(SocketChannel channel) throws IOException {
        byte[] req = "QUERY　TIME ORDER".getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(req.length);
        writeBuffer.put(req);
        writeBuffer.flip();
        channel.write(writeBuffer );
        System.out.println("Write request.");
    }
    
}
