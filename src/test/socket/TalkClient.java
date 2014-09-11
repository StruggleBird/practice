
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
            Socket socket = new Socket("127.0.0.1", TalkServer.PORT);
            BufferedReader inputReader = new LineNumberReader(new InputStreamReader(System.in));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String input = "";
            input = inputReader.readLine();
            while (!"bye".equals(input))
            {
                writer.println(input);
                writer.flush();
                System.out.println("client request:" + input);
                System.out.println("收到响应："+socketReader.readLine());
                input = inputReader.readLine();
            }

            inputReader.close();
            writer.close();
            socketReader.close();
            socket.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}