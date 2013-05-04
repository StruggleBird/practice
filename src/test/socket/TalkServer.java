
package test.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TalkServer
{
    public static void main(String[] args)
    {
        try
        {
            ServerSocket serverSocket = new ServerSocket(3000);

            Socket socket = serverSocket.accept();
            System.out.println("accpet");
            BufferedReader bReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter os = new PrintWriter(socket.getOutputStream());

            BufferedReader bSin = new BufferedReader(new InputStreamReader(System.in));

            for (int i = 0; i < 3; i++)
            {
                System.out.println("client:" + bReader.readLine());
            }
            //            String line = bSin.readLine();
            //            
            //            while (!"bye".equals(line))
            //            {
            //                os.print(line);
            //                os.flush();
            //                System.out.println("server:"+line);
            //                System.out.println("client:" + bReader.readLine());
            //                line = bSin.readLine();
            //            }

            bReader.close();
            os.close();
            bSin.close();
            socket.close();
            serverSocket.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
