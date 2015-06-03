

public class Examples {

	public static int staticTimes (int x, int y) {
		return x * y;
	}

	public int times (int x, int y) {
		return x * y;
	}
	
	public static int apply(Function f, int x) {
		return f.eval(x);
	}
	
	public int func(int x) {
		return 3 * x;
	}

	public static int staticFunc(int x) {
		return 3 * x;
	}
}
