package fibonacci;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FibonacciParallel6 {
	public static void main(String[] args) {
		int index = 30;
		int poolSize = Runtime.getRuntime().availableProcessors();
		for (int i = 5 * poolSize; i >= 1; i--) {
			long totalTime = 0;
			int num = 0;
			System.out.println("i=" + i);
			for (int j = 0; j < 10; j++) {
				long before = System.currentTimeMillis();
				ForkJoinPool pool = new ForkJoinPool(i);
				Fibonacci fib = new Fibonacci(index);
				num = pool.invoke(fib);
				pool.shutdown();
				long after = System.currentTimeMillis();
				totalTime += (after - before);
			}
			long averageTotalTime = totalTime / 10;
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
time with parallelism 40 = 1013
time with parallelism 39 = 732
time with parallelism 38 = 727
time with parallelism 37 = 726
time with parallelism 36 = 723
time with parallelism 35 = 713
time with parallelism 34 = 721
time with parallelism 33 = 714
time with parallelism 32 = 711
time with parallelism 31 = 708
time with parallelism 30 = 700
time with parallelism 29 = 697
time with parallelism 28 = 700
time with parallelism 27 = 710
time with parallelism 26 = 712
time with parallelism 25 = 692
time with parallelism 24 = 696
time with parallelism 23 = 693
time with parallelism 22 = 686
time with parallelism 21 = 685
time with parallelism 20 = 684
time with parallelism 19 = 682
time with parallelism 18 = 677
time with parallelism 17 = 678
time with parallelism 16 = 682
time with parallelism 15 = 675
time with parallelism 14 = 677
time with parallelism 13 = 675
time with parallelism 12 = 676
time with parallelism 11 = 687
time with parallelism 10 = 695
time with parallelism 9 = 710
time with parallelism 8 = 741
time with parallelism 7 = 829
time with parallelism 6 = 913
time with parallelism 5 = 1007
time with parallelism 4 = 1144
time with parallelism 3 = 1521
time with parallelism 2 = 1954
time with parallelism 1 = 3828
*/