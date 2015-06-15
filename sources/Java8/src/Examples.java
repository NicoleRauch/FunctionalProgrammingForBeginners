import java.util.function.IntUnaryOperator;



public class Examples {

	public static int staticTimes (int x, int y) {
		return x * y;
	}

	public int times (int x, int y) {
		return x * y;
	}
	
	public static int apply(IntUnaryOperator func, int arg) {
		return func.applyAsInt(arg);
	}
	
	public int func(int x) {
		return 3 * x;
	}

	public static int staticFunc(int x) {
		return 3 * x;
	}
}
