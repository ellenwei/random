import java.util.Arrays;
import java.util.Comparator;

public class GetSameSum {
	
	public static class NumWithDiff {
		int ceil;
		double diffWithCeil;
		        public NumWithDiff(int n, double c) {
		                this.ceil = n;
		                this.diffWithCeil = c;
		        }
	}

	
	/*
	Given an array of numbers A = [x1, x2, ..., xn] and T = Round(x1+x2+... +xn).
	We want to find a way to round each element in A such that 
	after rounding we get a new array B = [y1, y2, ...., yn] such that y1+y2+...+yn = T 
	where  yi = Floor(xi) or Ceil(xi), ceiling or floor of xi.
	We also want to minimize sum |x_i-y_i|
	*/
	// 思路就是先把所有floor加起来，然后看差多少，然后把多少个floor转成ceil
    // 转的时候按照num本身与ceil的距离排序
	public static int[] getNearlySum(double[] input){
		double sum = 0;
		int floorSum = 0;
		NumWithDiff[] diffArray = new NumWithDiff[input.length];
		for(int i=0; i<input.length; i++){
			sum+=input[i];
			int floor = (int) input[i];
			int ceil = floor;
			if(floor<input[i]){
				ceil++;
			}
			floorSum+=floor;
			diffArray[i] = new NumWithDiff(ceil, ceil-input[i]);
		}
		int target = (int)Math.round(sum);
		int diff = target-floorSum;
		Arrays.sort(diffArray, new Comparator<NumWithDiff>(){
			public int compare(NumWithDiff num1, NumWithDiff num2){
				
				if(num1.diffWithCeil<=num2.diffWithCeil){
					return -1;
				}
				return 1;
			}
			});
		for(NumWithDiff i:diffArray){
			System.out.print(i.diffWithCeil+ " ");
		}
		System.out.println(" ");
			int[] result = new int[input.length];
			int i=0;
			for (; i < diff; i++) {
                result[i] = diffArray[i].ceil; // 这些放ceil
        }
        for (; i < input.length; i++) {
                result[i] = diffArray[i].ceil - 1; // 剩下的只放floor
        }
        return result;	
	}
		
	public static void main(String[] argv){
			double[] arr = { 1.2, 2.3, 3.4}; 
			int[] res = getNearlySum(arr); 
			for (int i : res) 
				System.out.print(i + " "); 
	}
}
