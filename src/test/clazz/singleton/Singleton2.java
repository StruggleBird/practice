package test.clazz.singleton;

  
/**  
 * 单例模式改进  
 *   延迟加载类实例
 * @since jdk1.6  
 * @author 毛正吉  
 * @version 1.0  
 * @date 2010.06.08  
 *   
 */  
public class Singleton2 {   
       private static Singleton2 singleton2;
    /**  
     *   
     * @param args  
     */  
    public static void main(String[] args) { 
        System.out.println("begin");
        Singleton2 s1 = Singleton2.getInstance();   
        Singleton2 s2 = Singleton2.getInstance();   
        Singleton2 s3 = Singleton2.getInstance();   
        Singleton2 s4 = Singleton2.getInstance();   
        Singleton2 s5 = Singleton2.getInstance();   
  
        System.out.println(s1 == s2);   
        System.out.println(s2 == s3);   
        System.out.println(s3 == s4);   
        System.out.println(s4 == s5);   
    }   
  
       
    /**  
     * 私有构造  
     */  
    private Singleton2() {   
        System.out.println("Singleton Constructor...");   
    }   
  
    public static final Singleton2 getInstance() {   
        if (singleton2==null)
        {
            singleton2 = new Singleton2();
        }
        return singleton2;   
  
    } 
}  