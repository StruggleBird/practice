package test.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import junit.framework.TestCase;

public class ZipTest extends TestCase {


    public void testMakeFile() throws IOException, WriterException {
        File png = new File("a.png");
        if (!png.exists()) {
            png.createNewFile();
        }

        FileOutputStream os = new FileOutputStream(png);
        ZxingUtil.encode("http://baidu.com", 300, 300, os);

    }

    public void testZip() throws IOException, WriterException {

        FileOutputStream dest = new FileOutputStream("a.zip");
        ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest));

        ZipEntry entry = new ZipEntry("a.png");
        out.putNextEntry(entry);

        ZxingUtil.encode("http://baidu.com", 300, 300, out);

        out.closeEntry();
        out.close();

    }

    /**
     * 根据输入的文件与输出流对文件进行打包
     * 
     * @param File
     * @param org.apache.tools.zip.ZipOutputStream
     */
    public static void zipFile(File inputFile, ZipOutputStream ouputStream) {
        try {
            if (inputFile.exists()) {
                /**
                 * 如果是目录的话这里是不采取操作的， 至于目录的打包正在研究中
                 */
                if (inputFile.isFile()) {
                    FileInputStream IN = new FileInputStream(inputFile);
                    BufferedInputStream bins = new BufferedInputStream(IN, 512);
                    // org.apache.tools.zip.ZipEntry
                    ZipEntry entry = new ZipEntry(inputFile.getName());
                    ouputStream.putNextEntry(entry);
                    // 向压缩文件中输出数据
                    int nNumber;
                    byte[] buffer = new byte[512];
                    while ((nNumber = bins.read(buffer)) != -1) {
                        ouputStream.write(buffer, 0, nNumber);
                    }
                    // 关闭创建的流对象
                    bins.close();
                    IN.close();
                } else {
                    try {
                        File[] files = inputFile.listFiles();
                        for (int i = 0; i < files.length; i++) {
                            zipFile(files[i], ouputStream);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
