package org.zt.test.base;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class JVMLoad {
	public static void main(String[] args) throws ClassNotFoundException,
			Exception {
		URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
		for (int i = 0; i < urls.length; i++) {
			System.out.println(urls[i].getPath());
		}

		System.out.println("\r\n\r\n" + System.getProperty("java.ext.dirs"));
		ClassLoader extensionClassloader = ClassLoader.getSystemClassLoader()
				.getParent();
		System.out.println("the parent of extension classloader : "
				+ extensionClassloader.getParent());

		System.out.println("system class path:");
		System.out.println(System.getProperty("java.class.path"));

		URL[] urls2 = new URL[] { new URL(
				"file:/E:/JAVA_HOME/org.zt.test.jvm/bin/"),new URL(
						"file:/E:/WORK_HOME/JMW/jiemai/edm/webapp/WEB-INF/lib/ant-1.8.0.jar") };
		URLClassLoader classLoader = new URLClassLoader(urls2);
		String className = "main.HeapOOM";
//		String className = "org.apache.tools.zip.AsiExtraField";
		Class asiExtraFieldClass = classLoader.loadClass(className);
		System.out.println(Class.forName(className, false, classLoader));
		System.out.println(JVMLoad.class.getClassLoader());
		Object asiField = asiExtraFieldClass.newInstance();
		Method[] fields = asiExtraFieldClass.getDeclaredMethods();
		for (Method field : fields) {
			System.out.println(field.getName());
			if (field.getName().equals("foo")) {
				field.invoke(asiField, args);
			}
		}
		System.out.println(asiExtraFieldClass.getSuperclass());
		Class[] interfacesClasses = asiExtraFieldClass.getInterfaces();
		for (Class clazz : interfacesClasses) {
			System.out.println(clazz.getName());
		}
		System.out.println(asiExtraFieldClass.getClassLoader().getParent()
				.getParent());
		

	}
}
