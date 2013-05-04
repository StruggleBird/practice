package test.syntax;

public class Short
{
    public static void main(String[] args)
    {
        short  s1 = 1;
        s1=(short)(s1+2);  //必须转换类型
        
        s1+=1;
        System.out.println(s1);
    }
}
