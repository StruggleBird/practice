package test.base;

public class OuterClass {
	public class InnerClass{
		
	}
	
	public static void main(String[] args) {
		new OuterClass().new InnerClass();
	}
}
