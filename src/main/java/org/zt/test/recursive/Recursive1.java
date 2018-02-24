
package org.zt.test.recursive;

/**
 * 有这样一串数字 1,2,2,4,16,32... 求 第32个数字是多少
 * 求递归
 * @author zhoutao
 *
 */
public class Recursive1
{
    

    private static Long method(int count)
    {
        if (count == 1)
        {
            return 1L;
        }
        else if (count == 2)
        {
            return 2L;
        }
        else
        {
            return method(count - 1) * method(count - 2);
        }

    }

    public static void main(String[] args)
    {
        System.out.println(method(32));
        System.out.println(Long.MAX_VALUE);
        System.out.println(Double.MAX_VALUE > Long.MAX_VALUE);
    }
}
