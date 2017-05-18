package fibonacci;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FibonacciParallel5 {
	public static void main(String[] args) {
		int index = 30;
		int poolSize = Runtime.getRuntime().availableProcessors();
		for (int i = poolSize; i >= 1; i--) {
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
time with parallelism 8 = 593
30th Fibonacci number = 832040
time with parallelism 7 = 837
30th Fibonacci number = 832040
time with parallelism 6 = 914
30th Fibonacci number = 832040
time with parallelism 5 = 992
30th Fibonacci number = 832040
time with parallelism 4 = 1138
30th Fibonacci number = 832040
time with parallelism 3 = 1363
30th Fibonacci number = 832040
time with parallelism 2 = 1951
30th Fibonacci number = 832040
time with parallelism 1 = 3834
30th Fibonacci number = 832040
 */
 