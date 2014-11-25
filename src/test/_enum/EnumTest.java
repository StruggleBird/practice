package test._enum;

/**
 * @author Zhoutao
 * @date 2014年11月25日
 */
public class EnumTest {
	public interface State {
		String text();
	}

	public enum Operation implements State {
		PLUS {
			public String text() {
				return "加法";
			}
		},
		MINUS {

			@Override
			public String text() {
				return "减法";
			}}
	}

	public static void main(String[] args) {
		System.out.print(Operation.PLUS.text());
		System.out.print(Operation.PLUS.name());
		System.out.println();
		System.out.print(Operation.MINUS.text());
		System.out.print(Operation.MINUS.name());
	}
}
