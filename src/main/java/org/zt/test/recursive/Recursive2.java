
package org.zt.test.recursive;

public class Recursive2
{
    public static void main(String[] args)
    {
        System.out.println(method(1));
        ;
    }

    //1  4  10  22   
    private static int method(int day)
    {
        if (day == 10)
        {
            return 1;
        }
        else
        {
            //第day天=第(day+1)天加上一个的两倍
            return (method(day + 1) + 1) * 2;
        }

    }
}
