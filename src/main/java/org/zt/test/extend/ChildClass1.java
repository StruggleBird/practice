
package org.zt.test.extend;

public class ChildClass1 extends ParentClass
{
    /**
     * 该方法 编译不通过
     * Multiple markers at this line
     - Exception ExceptionBase is not compatible with throws clause in ParentClass.method()
     - overrides org.zt.test.继承.ParentClass.method
     */

    /*void method() throws ExceptionBase
    {
        System.out.println("childClass1");
    }*/
}
