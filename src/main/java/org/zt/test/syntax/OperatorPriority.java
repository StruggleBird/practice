
package org.zt.test.syntax;

public class OperatorPriority
{
    public static void main(String[] args)
    {
        int a = 1;
        int b = 2;
        System.out.println(++a == b);
        System.out.println(a++ == b);

        System.out.println(b == a++);
        System.out.println(b == ++a);

        a = 1;
        b = 2;
        System.out.println(a++);
        System.out.println(++b);
    }

    static void print(int i)
    {
        System.out.println(i);
    }
    
    
}
