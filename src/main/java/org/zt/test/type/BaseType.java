
package org.zt.test.type;

import org.junit.Test;
import org.zt.test.syntax.lifang;

public class BaseType {
	// Java八种基本数据类型
	// 变量类型 字节数 取值范围
	// byte 1字节 -128~127
	// short 2字节 -32768~~32767
	// int 4字节 -2147483648~2147483647
	// long 8字节 -2**64次方~~2的64次方-1
	// char 2字节 ------ （C和C++语言中是1字节）
	// float 4字节
	// double 8字节
	// boolean false/true 其中整型数据有 byte short int long 四种。
	// 实型数据有（单精度实数） float 和（双精度实数）double两种。
	// 字符型数据有char，因Java语言使用 Unicode字符集，因此其字符型数据在内存中占用2个字节
	// 布尔型用来表示关系运算和逻辑运算的结果 ，布尔型数据不可作整型数据使用，不同于c语言中的0和非0，它只有true和false两个值。

	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testEquals() {
		Integer a = 1;
		Integer b = 2;
		Integer c = 3;
		Integer d = 3;
		Long e = 32l;
		Long f = 32l;
		Long g = 3l;
		Integer h = 128;
		Integer i = 128;

		System.out.println(c == d);
		System.out.println(e == f);
		System.out.println(c == (a + b));
		System.out.println(c.equals(a + b));
		System.out.println(g == (a + b)); // 原因自动拆箱，做值对比
		System.out.println(g.equals(a + b)); // 输出false 原因 Long 和 Integer 类型不匹配，导致直接返回false
		System.out.println(h == i);
		System.out.println(h.equals(i));
	}

	@Test
	public void testEqualsString() {
		String a = "abc";
		String b = "ab" + "c";
		String c = new String("abc");

		System.out.println(a == b);
		System.out.println(a == c);
		System.out.println(a.equals(c));

	}

}
