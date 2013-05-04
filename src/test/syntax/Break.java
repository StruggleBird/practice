package test.syntax;


public class Break
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        ok:
            for (int i = 0; i < 4; i++)
            {
                for (int j = 0;; j++)
                {
                    if (j == 2)
                    {
                        break ok;
                    }
                    System.out.println(j);
                }
            }
    System.out.println("ok");
    }

}
