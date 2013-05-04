
package test.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TalkClient
{
    public static void main(String[] args)
    {
        try
        {
            Socket socket = new Socket("192.168.1.100", 3000);
            BufferedReader bReader = new LineNumberReader(new InputStreamReader(System.in));
            PrintWriter pWriter = new PrintWriter(socket.getOutputStream());
            BufferedReader bReaderIs = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String sin = "";
            sin = bReader.readLine();
            while (!"bye".equals(sin))
            {
                pWriter.write(sin);
                pWriter.flush();
                System.out.println("client:" + sin);
                //System.out.println("server:" + bReaderIs.readLine());
                sin = bReader.readLine();
            }

            bReader.close();
            pWriter.close();
            bReaderIs.close();
            socket.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}