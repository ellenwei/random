import java.util.LinkedList;
import java.util.List;

public class HouseRobber {
	static List<Integer> currentList = new LinkedList<>();
	
	public static int rob3(int[] nums) {
		if(nums.length == 0){
			return 0;
		}
		int prev = 0;
		List<Integer>prevList = new LinkedList<>();
		int current = nums[0];
		for(int i=1; i<nums.length; i++){
			int decision = Math.max(prev+nums[i], current);
			List<Integer>decisionList = currentList;
			if(decision == prev+nums[i]){
				decisionList = prevList;
				decisionList.add(i);
			}
			prev = current;
			prevList = currentList;
			current = decision;
			currentList = decisionList;
		}
		return current;
	}
	
	public static int rob2(int[] nums) {
		if(nums.length == 0){
			return 0;
		}
		int prev = 0;
		List<Integer> prevList = new LinkedList<>();
		int current = nums[0];
		currentList.add(0);
		for(int i=1; i<nums.length; i++){
			int decision = Math.max(prev+nums[i], current);
			List<Integer>decisionList = currentList;
			if(decision == prev+nums[i]){
				decisionList = prevList;
				decisionList.add(i);
			}
			prev = current;
			prevList = currentList;
			current = decision;
			currentList = decisionList;		
		}
		return current;
	}
	
	public static int rob1(int[] nums) {
        if(nums.length == 0){
        	return 0;
        }
        int prev = 0;
        List<Integer> previousList = new LinkedList<>();
        int current = nums[0];     
        currentList.add(0);
        for(int i=1; i<nums.length; i++){
        	int decision = Math.max(prev+nums[i], current);
        	List<Integer> temp = currentList;
        	if(decision == prev+nums[i]){
        		temp = previousList;
        		temp.add(i);
        	}
        	prev = current;
        	previousList = currentList;
        	current = decision;
        	currentList = temp;      	
        }
        return current;
    }
	
	public static void main(String[] argv){
		int[] nums = {1,2,1,3,4};
		System.out.println(rob1(nums));
		System.out.println("The indexes are ");
		for(int i:currentList){
			System.out.print(i+" ");
		}
	}
}
