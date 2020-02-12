

public class Calculator {

	public Calculator() {
	}
	/**
	 * Receives two double values and performs an action. If a is less than b, a*b, otherwise, a/b will be returned.
	 * @param a
	 * @param b
	 * @return the result of the action performed on and b.
	 * @throws ArithmeticException is b is 0;
	 */
	public double action(double a, double b) {
		double result = 0;
		if (a<b) {
			result = a*b;
		}else {
			result = a/b;
		}
		return result;
	}

}
