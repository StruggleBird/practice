
package test.thread;

public class ThreadTest2
{

    int i = 0;
    
    public static void main(String[] args)
    {
        
        ThreadTest2 t2 = new ThreadTest2();
        
        new Thread(t2.new MainThread()).start();
        for (int i = 0; i < 50; i++)
        {
            t2.main();
        }
        
    }

    private synchronized void main()
    {
        for (int i = 0; i < 100; i++)
        {
            System.out.println("Main:" + i);
        }
    }

    private synchronized void sub()
    {
        for (int i = 0; i < 10; i++)
        {
            System.out.println("Sub:" + i);
        }
    }

    class MainThread implements Runnable
    {

        public void run()
        {
            new SubThread().start();
            main();
            try
            {
                this.wait();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    class SubThread extends Thread
    {
        @Override
        public void run()
        {
            sub();
            this.notify();
        }
    }

}
