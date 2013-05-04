
package test.syntax;

public class Switch
{
    /**
     * 第一个switch  和 第二个 switch 分别输出什么 为什么？
     * @param args
     */
    public static void main(String[] args)
    {
        int a = 1;
        switch (a)
        {
            case 2:
                System.out.println("2");
            case 1:
                System.out.println(1);
            default:
                System.out.println("default");
        }

        switch (a)
        {
            case 2:
                System.out.println("2");
            default:
                System.out.println("default");
            case 1:
                System.out.println(1);
        }
    }
}
