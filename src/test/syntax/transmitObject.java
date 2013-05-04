/**
 * 
 */

package test.syntax;

import java.lang.reflect.Method;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoutao
 *
 */
public class transmitObject
{
    public static void main(String[] args)
    {
        Student s1 = new Student();
        s1.setName("s1");
        Student s2 = new Student();
        s2.setName("s2");
        change(s1, s2);
        System.out.println(s1.getName());
        System.out.println(s2.getName());
    }

    static void change(Student s1, Student s2)
    {
        s1.setName("c1");
        s2 = new Student();
        s2.setName("c2");
    }

}

class Student
{
    private String name;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
