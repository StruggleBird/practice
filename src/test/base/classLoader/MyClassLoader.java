package test.base.classLoader;

import java.net.URL;
import java.net.URLClassLoader;

public class MyClassLoader extends URLClassLoader {

	public MyClassLoader(URL[] urls) {
		super(urls);
	}
	
	
	public static void getCurrentClass(){
		StackTraceElement[] sts = Thread.currentThread().getStackTrace();
		for (StackTraceElement stackTraceElement : sts) {
			System.out.println(stackTraceElement);
		}
    	 System.out.println(sts[2].getClassName());
	}
	
	public static void main(String[] args) {
		getCurrentClass();
	}

}
