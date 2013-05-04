
package test.clazz.singleton;

/**  
 * 最简单的单例模式  
 * 类加载时实例化
 *   
 * @since jdk1.6  
 * @author 毛正吉  
 * @version 1.0  
 * @date 2010.06.08  
 *   
 */
public class Singleton3
{
    private static Singleton3 singleton2 = new Singleton3();

    /**  
     *   
     * @param args  
     */
    public static void main(String[] args)
    {
        System.out.println("begin");

        Singleton3 s1 = Singleton3.getInstance();
        Singleton3 s2 = Singleton3.getInstance();
        Singleton3 s3 = Singleton3.getInstance();
        Singleton3 s4 = Singleton3.getInstance();
        Singleton3 s5 = Singleton3.getInstance();

        System.out.println(s1 == s2);
        System.out.println(s2 == s3);
        System.out.println(s3 == s4);
        System.out.println(s4 == s5);
    }

    /**  
     * 私有构造  
     */
    private Singleton3()
    {
        System.out.println("Singleton Constructor...");
    }

    public static final Singleton3 getInstance()
    {
        return singleton2;
    }
}