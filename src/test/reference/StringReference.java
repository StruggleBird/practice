package test.reference;

public class StringReference {

	public static String value ;
	
	public static void foo( String value) {
		value = "2";

	}
	
	public static void main(String[] args) {
		value = new String("1");
		foo( value);
		System.out.println(value);
	}

}
