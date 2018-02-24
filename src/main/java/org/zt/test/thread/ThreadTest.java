
package org.zt.test.thread;

public class ThreadTest
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        new ThreadTest().init();
    }

    public void init()
    {
        final Business business = new Business();
        new Thread(new Runnable()
        {
            public void run()
            {
                for (int i = 0; i < 5; i++)
                {
                    business.sub(i);
                }
            }
        }

        ).start();

        for (int i = 0; i < 5; i++)
        {
            business.main(i);
        }
    }

    private class Business
    {
        boolean bShouldSub = true;//这里相当于定义了控制该谁执行的一个信号灯

        public synchronized void main(int i)
        {
            if (bShouldSub)
                try
                {
                    this.wait();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }

            for (int j = 0; j < 5; j++)
            {
                System.out.println(Thread.currentThread().getName() + ":i=" + i + ",j=" + j);
            }
            bShouldSub = true;
            this.notify();

        }

        public synchronized void sub(int i)
        {
            if (!bShouldSub)
                try
                {
                    this.wait();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }

            for (int j = 0; j < 5; j++)
            {
                System.out.println(Thread.currentThread().getName() + ":i=" + i + ",j=" + j);
            }
            bShouldSub = false;
            this.notify();
        }
    }
}
