package test.recursive;
/**
 * 分解质因数
 * @author Administrator
 *
 */
public class Recursive3
{
    
    public static void main(String[] args)
    {
        method(10);
    }
    static int method(int num)
    {
        for (int i = 2; i <= num; i++)
        {
            if (num % i == 0)
            {
                if (num == i)
                {
                    System.out.print(i);
                }
                else
                {
                    System.out.print(i + "*");
                }

                return method(num / i);
            }
        }
        return 0;
    }
}
