/**
 * 
 */
package org.zt.test.nio;

import java.nio.ByteBuffer;

import junit.framework.TestCase;

/**
 * @author Ternence
 * @create 2015年5月1日
 */
public class ByteBufferTest extends TestCase{
    public void testPut(){
        String content = "abc";
        byte[] array = content.getBytes();
        
        ByteBuffer buffer = ByteBuffer.wrap(array );
//        buffer.put(array);
        buffer.flip();
        
        byte [] output= new byte[buffer.remaining()];
        buffer.get(output);
        System.out.println(new String(output));
    }
}
