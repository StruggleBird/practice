
package test.thread;

import test.base.OuterClass;
import junit.framework.TestCase;

public class ThreadTest3 extends TestCase
{
	public void testThreadLocal() {
		ThreadLocal<OuterClass> tl= new ThreadLocal<OuterClass>();
	}
}
