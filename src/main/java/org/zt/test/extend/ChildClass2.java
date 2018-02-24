
package org.zt.test.extend;

public class ChildClass2 extends ParentClass
{
    /**
     * 父类中default访问修饰符 默认为protected 包内可访问
     */
    void method() throws ExceptionSub1
    {
        System.out.println("childClass2" + i);
    }
}
