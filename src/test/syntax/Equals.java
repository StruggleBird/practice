
package test.syntax;

public class Equals
{
    /**
     * 下面的运算会输出什么？
     * @param args
     */
    public static void main(String[] args)
    {
        String s1 = "a";
        String s2 = "a";
        StringBuffer sBuffer1 = new StringBuffer("a");
        StringBuffer sBuffer2 = new StringBuffer("a");

        System.out.println(s1 == s2);
        System.out.println(s1.equals(new String(s2)));

        System.out.println(s1.equals(sBuffer1));
        System.out.println(sBuffer1.equals(sBuffer2));
    }

}
