
package test.extend;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Test
{
    /**
     * java 中访问修饰符
     * public   可以被任何类访问     
    protected   可以被同一包中的所有类访问
    可以被所有子类访问   子类没有在同一包中也可以访问
    private 只能够被当前类的方法访问     
    缺省（无访问修饰符）  可以被同一包中的所有类访问   如果子类没有在同一个包中，也不能访问
     * 
     */
    public static void main(String[] args) throws ExceptionBase
    {
        ParentClass class1 = new ChildClass1();
        class1.method();
        ParentClass class2 = new ChildClass2();
        Field field = ParentClass.class.getDeclaredFields()[0];
        getModifier(field);
        class2.method();
    }

    static void getModifier(Field field)
    {
        System.out.println(Modifier.toString(field.getModifiers()));
    }
}
