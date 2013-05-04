
package test.thread;

/**
 * 设计4个线程，其中两个线程每次对j增加1，另外两个线程对j每次减少1。
 * @author zhoutao
 *
 */
public class ThreadTest1
{
    int i = 0;
    int j = 0;

    public static void main(String[] args)
    {
        ThreadTest1 test1 = new ThreadTest1();
        for (int i = 0; i < 4; i++)
        {
            Thread t = null;
            if (i > 1)
            {
                t = new Thread(test1.new Thread1("-"));
            }
            else
            {
                t = new Thread(test1.new Thread1("+"));
            }
            t.start();
        }
    }

    private synchronized void Inc()
    {
        j++;
        System.out.println("i:" + (++i) + "+:" + j);
    }

    private synchronized void Dec()
    {
        j--;
        System.out.println("i:" + (++i) + "-:" + j);
    }

    class Thread1 implements Runnable
    {
        String method;

        public Thread1(String method)
        {
            this.method = method;
        }

        public void run()
        {

            if (method != null)
            {

                if ("+".equals(method))
                {
                    Inc();
                }
                else if ("-".equals(method))
                {
                    Dec();
                }
            }

        }
    }

}
