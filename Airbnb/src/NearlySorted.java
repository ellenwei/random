import java.util.Comparator;
import java.util.PriorityQueue;

public class NearlySorted {
	/**
	 * Given an array of n elements, 
	 * where each element is at most k away from its target position, 
	 * devise an algorithm that sorts in O(n log k) time.
	 * For example, let us consider k is 2, an element at index 7 in the sorted array, 
	 * can be at indexes 5, 6, 7, 8, 9 in the given array.
     * Time: O(nlogk)
     * Space: O(k)
     */
    public static void sort(int[] arr, int k){
        Comparator<Integer> comparator = new Comparator<Integer>(){
            public int compare(Integer n1, Integer n2){
                return n1 - n2;
            }
        };

        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(k + 1, comparator);
        for(int i = 0; i < k + 1; i++){
            queue.offer(arr[i]);
        }

        int index = 0;
        int i = k + 1;
        while(i < arr.length){
            arr[index] = queue.poll();
            queue.offer(arr[i]);
            index++;
            i++;
        }

        while(index < arr.length){
            arr[index] = queue.poll();
            index++;
        }
    }
    
    public static void main(String[] args) {
        //int[] arr = {3,4,1,2,8,9,6,7};
        int[] arr = {5,4,1,7,2,3};
        sort(arr, 2);
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
