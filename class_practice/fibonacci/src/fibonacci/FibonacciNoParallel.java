package fibonacci;

public class FibonacciNoParallel {
	public static void main(String[] args) {
		int index = 30;
		long before = System.currentTimeMillis();
		Fibonacci fib = new Fibonacci(index);
		int num = fib.compute();
		long after = System.currentTimeMillis();
		System.out.println("time without parallelism = " + (after - before));
		System.out.println(index + "th Fibonacci number = " + num);
	}

	static class Fibonacci {
		private int n;

		Fibonacci(int n) {
			this.n = n;
		}

		protected Integer compute() {
			if (n == 0 || n == 1) {
				return n;
			}
			Fibonacci f1 = new Fibonacci(n - 1);
			Fibonacci f2 = new Fibonacci(n - 2);
			long tmp = n * 1000;
			for (long i = 0; i < tmp; i++) {
			}
			this.n = f1.compute() + f2.compute();
			return this.n;
		}
	}
}

/*
time without parallelism = 1537
30th Fibonacci number = 832040
 */ 
