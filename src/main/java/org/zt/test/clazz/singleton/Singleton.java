package org.zt.test.clazz.singleton;

  
/**  
 * 单例模式改进  
 *   延迟加载类实例的改进
 * @since jdk1.6  
 * @author 毛正吉  
 * @version 1.0  
 * @date 2010.06.08  
 *   
 */  
public class Singleton {   
       
    /**  
     *   
     * @param args  
     */  
    public static void main(String[] args) { 
        System.out.println("begin");
        Singleton s1 = Singleton.getInstance();   
        Singleton s2 = Singleton.getInstance();   
        Singleton s3 = Singleton.getInstance();   
        Singleton s4 = Singleton.getInstance();   
        Singleton s5 = Singleton.getInstance();   
  
        System.out.println(s1 ==s2);   
        System.out.println(s2 == s3);   
        System.out.println(s3 == s4);   
        System.out.println(s4 == s5);
        
        
    }   
  
    /**  
     * 私有内部类  
     * @author 毛正吉  
     *  
     */  
    private static class SingletonCreate {   
        private static final Singleton instance = new Singleton();   
    }   
       
    /**  
     * 私有构造  
     */  
    private Singleton() {   
        System.out.println("Singleton Constructor...");   
    }   
  
    public static final Singleton getInstance() {   
  
        return SingletonCreate.instance;   
  
    }   
}  