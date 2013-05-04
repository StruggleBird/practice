
package test.IO;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * 
 * @author zhoutao
 *
 */
public class ReadCache
{

    /**
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException
    {
        String file = "F:\\share\\my.cnf";
        InputStream is = new FileInputStream(file);
        Reader reader = new InputStreamReader(is, "utf-8");
        BufferedReader br = new BufferedReader(reader);
        String line = "";
        while ((line = br.readLine()) != null)
        {
            System.out.println(line);
        }

        //read(file);
    }

    private static void read(String file) throws FileNotFoundException, IOException
    {
        Reader reader = new FileReader(file);

        BufferedReader br = new BufferedReader(reader);

        String line = "";
        while ((line = br.readLine()) != null)
        {
            System.out.println(line);
        }
    }
}
