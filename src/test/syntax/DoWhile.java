package test.syntax;

/**
 * 最后输出的结果 是多少？
 * @author zhoutao
 *
 */
public class DoWhile
{
    public static void main(String[] args)
    {
        int i = 1;
        int j = 10;
        do
        {
            if (i++ < --j)
            {
                System.out.println("i:"+i+"  j:"+j);
                continue;
            }
        } while (i < j);
    }
}
