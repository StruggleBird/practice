/**
 * 
 */
package test.io;

import java.io.File;

import org.junit.Test;

/**
 * @author Ternence
 * @create 2015年1月31日
 */
public class FileTest {
  @Test
  public void testSeparator() {
    System.out.println(File.pathSeparator);
    System.out.println(File.separator);
    
    String path = this.getClass().getResource("/").getPath()+"a.txt";
    System.out.println(new File(path).exists());
  }
}
