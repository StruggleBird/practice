/**
 * 
 */
package test.math;

import org.junit.Test;

/**
 * @author Zhoutao
 * @create 2014年10月9日
 */
public class MathTest {

	/**
	 * 位操作符
	 * 用于状态字段将会方便判断或者和并且（包含）的关系操作
	 * @create 2014年10月9日
	 */
	@Test
	public void testBitOperation() {
		int one = 1 << 1;
		int two = 1 << 2;
		int three = 1 << 3;
		System.out.println(Integer.toBinaryString(one));
		System.out.println(Integer.toBinaryString(two));
		System.out.println(Integer.toBinaryString(three));
		System.out.println(one | two);
		System.out.println(two | three);
		System.out.println(Integer.toBinaryString(two | three));
		System.out.println(two );
		System.out.println((one | two) & (three | two));
		System.out.println(((one ) & (one | two)) == one);
		System.out.println(Integer.toBinaryString(8));
	}

}
