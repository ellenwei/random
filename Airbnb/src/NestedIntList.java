import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class NestedIntList {
	private int value;
	private boolean isNumber;
	private List<NestedIntList> intList;
	
	public NestedIntList(int value){
		this.value = value;
		isNumber = true;
	}
	
	public NestedIntList(){
		intList = new ArrayList<>();
		isNumber = false;
	}
	
	public void add(NestedIntList num){
		this.intList.add(num);
	}
	
	
	//If encounters '[', 
	// push current NestedInteger to stack and start a new one.
	//If encounters ']', 
	// end current NestedInteger and pop a NestedInteger from stack to continue.
	//If encounters ',', 
	// append a new number to curr NestedInteger, if this comma is not right after a brackets.
	//Update index l and r, 
	// where l shall point to the start of a integer substring, while r shall points to the end+1 of substring.
	public NestedIntList miniParser(String s){
		if(s == null || s.length() == 0){
			return null;
		}
		if(s.charAt(0) != '['){
			int num = Integer.parseInt(s);
			return new NestedIntList(num);
		}
		int left = 0;
		Stack<NestedIntList> stack = new Stack<>();
		NestedIntList current = null;
		for (int right=0; right<s.length(); right++){
	        char currentCharacter = s.charAt(right);
	        if (currentCharacter == '['){
	            if (current != null){
	                stack.push(current);
	            }
	            current = new NestedIntList();
	            left = right+1;
	        }else if (currentCharacter == ']'){
	            String num = s.substring(left,right);
	            if (num.length()>0){
	                current.add(new NestedIntList(Integer.parseInt(num)));
	            }
	            if (!stack.isEmpty()){
	                NestedIntList pop = stack.pop();
	                pop.add(current);
	                current = pop;
	            }
	            left = right+1;
	        }else if (currentCharacter == ','){
	            if (s.charAt(right-1) != ']') {
	                String num = s.substring(left,right);
	                current.add(new NestedIntList(Integer.parseInt(num)));
	            }
	            left = right+1;
	        }
	        
	    }
		return current;
	}
	
	public String toString(){
		if(this.isNumber){
			return this.value+"";
		}else{
			return this.intList.toString();
		}		
	}
}
