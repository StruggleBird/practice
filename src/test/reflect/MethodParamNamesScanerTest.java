package test.reflect;

import java.lang.reflect.Method;
import java.util.List;

import junit.framework.TestCase;

public class MethodParamNamesScanerTest extends TestCase {



    public void testScaner(String aaaa, Integer bbb) {
        Method[] methods = MethodParamNamesScaner.class.getMethods();
        System.out.println(methods.length);
        List<String> paramNams = MethodParamNamesScaner.getParamNames(methods[1]);
        System.out.println(paramNams);
    }

    public static void main(String[] args) {
        Method[] methods = MethodParamNamesScanerTest.class.getMethods();
        List<String> paramNams = MethodParamNamesScaner.getParamNames(methods[0]);
        for (String paramName : paramNams) {
            System.out.println(paramName);
        }
    }

}
