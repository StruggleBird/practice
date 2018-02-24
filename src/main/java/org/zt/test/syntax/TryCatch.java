
package org.zt.test.syntax;

public class TryCatch
{
    public static void main(String[] args)
    {
        method();
    }

    private static void method()
    {
        try
        {
            throw new RuntimeException("try");
        }
        catch (Exception e)
        {
            System.out.println("exception");
            return;
        }
        finally
        {
            System.out.println("finally");
        }
        //System.out.println("end");
    }
}
