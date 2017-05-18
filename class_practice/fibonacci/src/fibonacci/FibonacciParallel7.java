package fibonacci;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FibonacciParallel7 {
	public static void main(String[] args) {
		int index = 30;
		int poolSize = Runtime.getRuntime().availableProcessors();
		for (int i = 5 * poolSize; i >= 1; i--) {
			long totalTime = 0;
			int num = 0;
			for (int j = 0; j < 20; j++) {
				long before = System.currentTimeMillis();
				ForkJoinPool pool = new ForkJoinPool(i);
				Fibonacci fib = new Fibonacci(index);
				num = pool.invoke(fib);
				pool.shutdown();
				long after = System.currentTimeMillis();
				totalTime += (after - before);
			}
			long averageTotalTime = totalTime / 20;
			System.out.println("time with parallelism " + i + " = " + averageTotalTime);
			// System.out.println(index + "th Fibonacci number = " + num);
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
			long tmp = n * 1000;
			for (long i = 0; i < tmp; i++) {
			}
			this.n = f2.compute() + f1.compute();
			return this.n;
		}
	}
}
/*
time with parallelism 40 = 1533
time with parallelism 39 = 1491
time with parallelism 38 = 1496
time with parallelism 37 = 1492
time with parallelism 36 = 1529
time with parallelism 35 = 1583
time with parallelism 34 = 1506
time with parallelism 33 = 1543
time with parallelism 32 = 1647
time with parallelism 31 = 1504
time with parallelism 30 = 1494
time with parallelism 29 = 1510
time with parallelism 28 = 1499
time with parallelism 27 = 1499
time with parallelism 26 = 1538
time with parallelism 25 = 1494
time with parallelism 24 = 1547
time with parallelism 23 = 1490
time with parallelism 22 = 1529
time with parallelism 21 = 1506
time with parallelism 20 = 1567
time with parallelism 19 = 1512
time with parallelism 18 = 1490
time with parallelism 17 = 1486
time with parallelism 16 = 1519
time with parallelism 15 = 1607
time with parallelism 14 = 1498
time with parallelism 13 = 1487
time with parallelism 12 = 1491
time with parallelism 11 = 1493
time with parallelism 10 = 1519
time with parallelism 9 = 1487
time with parallelism 8 = 1491
time with parallelism 7 = 1489
time with parallelism 6 = 1496
time with parallelism 5 = 1489
time with parallelism 4 = 1491
time with parallelism 3 = 1487
time with parallelism 2 = 1492
time with parallelism 1 = 1482
*/