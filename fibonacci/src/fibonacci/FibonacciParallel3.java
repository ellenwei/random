package fibonacci;


import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FibonacciParallel3 {
	public static void main(String[] args) {
		int index = 30;
		long before = System.currentTimeMillis();
		ForkJoinPool pool = new ForkJoinPool();
		Fibonacci fib = new Fibonacci(index);
		int num = pool.invoke(fib);
		pool.shutdown();
		long after = System.currentTimeMillis();
		System.out.println("time with parallelism = " + (after - before));
		System.out.println(index + "th Fibonacci number = " + num);
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
time with parallelism = 737 
30th Fibonacci number = 832040
 */
