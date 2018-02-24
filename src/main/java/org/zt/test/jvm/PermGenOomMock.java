package org.zt.test.jvm;

import java.io.File;
import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class PermGenOomMock {
	public static void main(String[] args) throws Exception {

		URL url = new File("/home/ternence/workspace/github/sweet-java-lib/target/classes").toURI().toURL();
		URL[] urls = { url };

		// 获取有关类型加载的JMX接口
		ClassLoadingMXBean loadingBean = ManagementFactory.getClassLoadingMXBean();
		// 用于缓存类加载器
		List<ClassLoader> classLoaders = new ArrayList<ClassLoader>();
		while (true) {
			// 加载类型并缓存类加载器实例
			ClassLoader classLoader = new URLClassLoader(urls);
			classLoaders.add(classLoader);
			Class<?> class1 = classLoader.loadClass("org.zt.sweet.http.URIs");
			
			// 显示数量信息（共加载过的类型数目，当前还有效的类型数目，已经被卸载的类型数目）
			System.out.printf("total:%s ,active: %s,unloaded: %s \r\n", loadingBean.getTotalLoadedClassCount(),loadingBean.getLoadedClassCount(),loadingBean.getUnloadedClassCount());
		}
	}
}