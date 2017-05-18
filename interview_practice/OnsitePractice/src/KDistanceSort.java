import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class KDistanceSort {
	/**
	 * Given an array of n elements, 
	 * where each element is at most k away from its target position, 
	 * devise an algorithm that sorts in O(n log k) time.
	 * For example, let us consider k is 2, an element at index 7 in the sorted array, 
	 * can be at indexes 5, 6, 7, 8, 9 in the given array.
     * Time: O(nlogk)
     * Space: O(k)
     */
	
/**
 * one method is to have a moving window of length k.
 *  Find the smallest element of this window and then move the window to the right. 
 * (Represent the window as a heap to do this efficiently.)
 * @param arr
 * @param k
 */
    public static void sort1(int[] arr, int k){
    	Comparator<Integer> comparator = new Comparator<Integer>(){
    		public int compare(Integer int1, Integer int2){
    			return int1.compareTo(int2);
    		}
    	};
    	Queue<Integer> queue = new PriorityQueue<>(k+1, comparator); 
    	for(int i=0; i<k+1; i++){
    		queue.offer(arr[i]);
    	}
    	int index=0;
    	int i=k+1;
    	
    	while(i<arr.length){
    		arr[index] = queue.poll();
    		queue.offer(arr[i]);
    		i++;
    		index++;
    	}
    	
    	while(index<arr.length){
    		arr[index] = queue.poll();
    		index++;
    	}
    	
    	return;
    }
    
	public static void main(String[] argv){
		int[] arr = {5,4,1,7,2,3};
        sort1(arr, 2);
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
	}
}
