import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class InterviewPractice {
	

	//monkey crosses river
	public static int findTimeMonkey(int[] input, int dis){
		int riverLength = input.length;
		// find max in array
		int max = -1;
		for (int i=0; i<riverLength; i++){
			if (input[i]>max){
				max = input[i];
			}
		}
		System.out.println("size is "+max);
		int[] newArr = new int[max+1];
		Arrays.fill(newArr, -1);
		for (int i=0; i< riverLength; i++){
			if (input[i] == -1){
				continue;
			}
			System.out.println("index for new array is "+input[i] +" value is " +i);
			newArr[input[i]] = i;
		}
		return findTime(newArr, dis, riverLength);
	}
	
	// frog corsses river
	public static int findTime(int[] input, int dis, int target){
		Set<Integer>reachable = new HashSet<Integer>();
		Set<Integer>unused = new HashSet<Integer>();
		int bound = 0;
		for (int i=1; i<=dis; i++){
			reachable.add(i);
			System.out.println("number added to reachable is "+i);
		}
		for(int i=0; i<input.length; i++){
			if (reachable.size() >= target){
				return i;
			}else if (input[i]<=bound){
				continue;
			}else if (!reachable.contains(input[i])){
				System.out.println("number added to unused is "+input[i]);

				unused.add(input[i]);
			}else{
				int endPoint = input[i]+dis;
				System.out.println("initial endPoint is "+endPoint);
				for (int j=reachable.size()+1;j<=endPoint; j++){
					if (unused.contains(j)){
						endPoint = j+dis;
						System.out.println("endPoint has been updated to  "+endPoint);
					}
					reachable.add(j);
					System.out.println("increasing j is "+j);

				}
			
			bound = reachable.size()-dis;
			if (reachable.size() >= target){
				return i;
			}
			}
		}
		return -1;
	}
	
	public static int maxSlidingWindowSmallestNum(int[] nums, int k) {
		int result = Integer.MIN_VALUE;
        if (nums.length == 0 || k<=0){
            return result;
        }
        int length = nums.length;     
        Deque<Integer> queue = new ArrayDeque<Integer>();
        for (int i=0; i<length; i++){
            while(!queue.isEmpty() && queue.peek()<i-k+1){
                queue.poll();
            }
            while(!queue.isEmpty() && nums[queue.peekLast()]>nums[i]){
                queue.pollLast();
            }
            queue.offer(i);
            if (i>=k-1){
            	result = Math.max(result, nums[queue.peek()]);
            }
        }
        return result;
    }
	
	
	
	
	public static LinkedHashMap<Integer, Double> sortHashMapByValues(
	        Map<Integer, Double> map) {
	    List<Integer> mapKeys = new ArrayList<>(map.keySet());
	    List<Double> mapValues = new ArrayList<>(map.values());
	    Collections.sort(mapValues);
	    Collections.sort(mapKeys);

	    LinkedHashMap<Integer, Double> sortedMap =
	        new LinkedHashMap<>();

	    Iterator<Double> valueIt = mapValues.iterator();
	    while (valueIt.hasNext()) {
	        Double val = valueIt.next();
	        Iterator<Integer> keyIt = mapKeys.iterator();

	        while (keyIt.hasNext()) {
	            Integer key = keyIt.next();
	            Double comp1 = map.get(key);
	            Double comp2 = val;

	            if (comp1.equals(comp2)) {
	                keyIt.remove();
	                sortedMap.put(key, val);
	                break;
	            }
	        }
	    }
	    return sortedMap;
	}
	
	
	

        
        


	
//	public static void rounding(double[] inputArray, int t){
//		// find the sum of all the floors of all the elements in the array, denoted as sumF
//		int sumF = 0;
//		int inputSize = inputArray.length;
//		for (int i=0; i<inputSize; i++){
//			sumF += Math.floor(inputArray[i]);
//		}
////		// find the sum of all the ceilings of all the elements in the array, denoted as sumC
////		int sumC = 0;
//////		for (int i=0; i<inputSize; i++){
//////			sumC+= Math.ceil(inputArray[i]);
//////		}
//		int delta = sumF-t;
//		Map<Integer, Double> map = new HashMap<Integer, Double>();
//		NumWithDiff[] decimalArray = new NumWithDiff[inputSize];
//		for (int i=0; i<inputSize; i++){
//			decimalArray[i] = new NumWithDiff(i, inputArray[i]-Math.floor(inputArray[i]));
//		}
//		Arrays.sort(decimalArray, new Comparator<NumWithDiff>(){
//			@Override
//			public int compare(NumWithDiff num1, NumWithDiff num2) {
//				// TODO Auto-generated method stub
//				if (num1.difference <= num2.difference-1)
//					return 1;
//				return 0;
//			}
////		int[] result = new int[inputSize];
//////		for (int i=0; i<inputSize; i++){
//////			if (i<delta){
//////				result[i] = decimalArray[i].index;
//////			}
//////		}
//////		});
////		/**
////		 * use a class to store its index and inputArray[i]-Math.floor(inputArray[i])
////		 * use Arrays.sort()
////		 */
////		
////		//double[] result = new double[inputSize];
////		
////	}
	
	public static void mergeSort(int[] numbers, int left, int right){    
	      if (right > left)
	      {
	        int mid = (right + left) / 2;
	        mergeSort(numbers, left, mid);
	        mergeSort(numbers, (mid + 1), right);    
	        merge( left, mid+1, right, numbers);
	      }
	}
	
	public static void merge(int left, int mid, int right, int[] numbers){
		int [] temp = new int[25];
        int i, left_end, num_elements, tmp_pos;
    
        left_end = (mid - 1);
        tmp_pos = left;
        num_elements = (right - left + 1);
    
        while ((left <= left_end) && (mid <= right))
        {
            if (numbers[left] <= numbers[mid])
                temp[tmp_pos++] = numbers[left++];
            else
                temp[tmp_pos++] = numbers[mid++];
        }
    
        while (left <= left_end)
            temp[tmp_pos++] = numbers[left++];
 
        while (mid <= right)
            temp[tmp_pos++] = numbers[mid++];
 
        for (i = 0; i < num_elements; i++)
        {
            numbers[right] = temp[right];
            right--;
        }
	}
	
	public static int[] maxRunOfPositiveNumbers(int[] arr) {
		int size = arr.length;
		int maxStart = 0;
		int maxLength = 0;
		int current = 0;
		while (current < size){
			while (current<size && arr[current]<=0){
				current++;
			}
			int start = current;
			while(current<size && arr[current]>0){
				current++;
			}
			int length = current-start;
			if (length>maxLength){
				maxLength = length;
				maxStart = start;
			}
		}
		return Arrays.copyOfRange(arr, maxStart, maxStart+maxLength);
//		  final int n = arr.length;
//		  int maxstart = 0;
//		  int maxlen = 0;
//		  int i = 0;
//
//		  while (i < n) {
//		    while (i < n && arr[i] <= 0)
//		      i++;
//
//		    int start = i;
//		    while (i < n && arr[i] > 0)
//		      i++;
//		    int len = i - start;
//
//		    if (len > maxlen) {
//		      maxstart = start;
//		      maxlen = len;
//		    }
//		  }
//		  return Arrays.copyOfRange(arr, maxstart, maxstart + maxlen);
		}
	
	
	
	
	//Given an array of numbers A = [x1, x2, ..., xn] and T = Round(x1+x2+... +xn). 
	//We want to find a way to round each element in A such that after rounding,
	//we get a new array B = [y1, y2, ...., yn] such that y1+y2+...+yn = T 
	//where  yi = Floor(xi) or Ceil(xi)
	//We also want to minimize sum of |x_i-y_i|
	  
	public static void main(String[] argv){
		
		
		
		
		int[] test1 = {1,3,-1,-3,5,3,6,7};
		int resultWin = maxSlidingWindowSmallestNum(test1, 3);
		System.out.println("The result for max window is "+resultWin);
		int[] test2 = {};
		int[] numbers = { 3, 8, 7, 5, 2, 1, 9, 6, 4 };
        int len = 9;
        //int[] times = {5,1,0,0,3};
        int[] times = {1,3,1,4,2,5};
        int result = findTime(times, 3,7);
        System.out.println("the time frog jumps successfully across is "+ result);
        
        int[] river = {1,-1,0,2,3,5};
        int resultRiver = findTimeMonkey(river, 3);
        System.out.println("the time frog jumps successfully across is "+ resultRiver);
        
        System.out.println("MergeSort By Recursive Method");
 
        mergeSort(numbers, 0, len-1);
        for (int i = 0; i < 9; i++)
            System.out.println(numbers[i]);
		
		
		
		
	    
	    int[] findMaxPositive = {-1, 2, 3, -4, 6, 12, 8, 9, -3, -5};
	    int[] result1 = maxRunOfPositiveNumbers(findMaxPositive);
	    System.out.println("The longest positive number sequence is ");
	    for (int i=0; i<result1.length; i++){
	    	System.out.print(result1[i]+ " ");
	    }
	}
}
