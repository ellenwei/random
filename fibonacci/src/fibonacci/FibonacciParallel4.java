package fibonacci;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FibonacciParallel4 {
	public static void main(String[] args) {
		int index = 30;
		int poolSize = Runtime.getRuntime().availableProcessors();
		for (int i = 1; i <= poolSize; i++) {
			long before = System.currentTimeMillis();
			ForkJoinPool pool = new ForkJoinPool(i);
			Fibonacci fib = new Fibonacci(index);
			int num = pool.invoke(fib);
			pool.shutdown();
			long after = System.currentTimeMillis();
			System.out.println("time with parallelism " + i + " = " + (after - before));
			System.out.println(index + "th Fibonacci number = " + num);
		}
	}

	static class Fibonacci extends RecursiveTask<Integer> {
		private static final long serialVersionUID = 1;
		private int n;

		Fibonacci(int n) {
			this.n = n;
		}

		public int getNum() {
			return this.n;
		}

		protected Integer compute() {
			if (n == 0 || n == 1) {
				return n;
			}
			Fibonacci f1 = new Fibonacci(n - 1);
			Fibonacci f2 = new Fibonacci(n - 2);
			f1.fork();
			f2.fork();
			long tmp = n * 1000;
			for (long i = 0; i < tmp; i++) {
			}
			this.n = f2.join() + f1.join();
			return this.n;
		}
	}
}
/*
time with parallelism 1 = 3572
30th Fibonacci number = 832040
time with parallelism 2 = 1855
30th Fibonacci number = 832040
time with parallelism 3 = 1302
30th Fibonacci number = 832040
time with parallelism 4 = 1101
30th Fibonacci number = 832040
time with parallelism 5 = 1051
30th Fibonacci number = 832040
time with parallelism 6 = 908
30th Fibonacci number = 832040
time with parallelism 7 = 804
30th Fibonacci number = 832040
time with parallelism 8 = 738
30th Fibonacci number = 832040
 */