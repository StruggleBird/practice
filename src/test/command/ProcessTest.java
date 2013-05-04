
package test.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;

public class ProcessTest
{
    public static void main(String[] args)
    {
        try
        {
            Reader in = new InputStreamReader(System.in);

            int c;
            StringBuffer sbIn = new StringBuffer();
            while ((c = in.read()) > 0)
            {
                char ch = (char)c;
                if (ch == '\n')
                {
                    exec(sbIn.toString());
                    sbIn = new StringBuffer();
                }
                else
                {
                    sbIn.append(ch);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static void exec(String command)
    {
        try
        {
            System.out.println("command:" + command);
            Process process = Runtime.getRuntime().exec(command);
            PrintWriter writer = new PrintWriter(process.getOutputStream());
            //new CommandThread(writer).start();
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader brErr = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            String s = null;
            while ((s = br.readLine()) != null)
            {
                System.out.println(s);
            }

            while ((s = brErr.readLine()) != null)
            {
                System.out.println(s);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
    }

    static class CommandThread extends Thread
    {
        PrintWriter writer;
        BufferedReader br = null;

        CommandThread(PrintWriter writer)
        {
            this.writer = writer;
            br = new BufferedReader(new InputStreamReader(System.in));
            this.setDaemon(true);
        }

        @Override
        public void run()
        {
            try
            {
                String cmd = null;
                while ((cmd = br.readLine()) != null)
                {
                    writer.println(cmd);
                    writer.flush();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}