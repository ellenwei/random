import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class ExpressionTree {
	static String expressionTree(String expressionString){
		String[] input = expressionString.trim().split("/");
		String expression = input[0];
		char[] operation = input[1].toCharArray();
		for(int i=0; i<operation.length; i++){
			char opr = operation[i];
			if(opr == 'R'){
				expression = reverse(expression);
			}
			if(opr == 'S'){
				expression = simplify(expression);
			}
		}
		return expression;
	}
	
	static String simplify(String expression){
		Stack<Integer> stack = new Stack<>();
		boolean firstLeft = true;
		List<Integer> queue = new LinkedList<>();
		for(int i=expression.length()-1; i>=0; i--){
			char current = expression.charAt(i);
			if(current == ')'){
				stack.push(i);
			}
			if(current == '('){
				int rightIndex = stack.pop();
				if(firstLeft){
					int leftIndex = i;
					queue.add(rightIndex);
					queue.add(leftIndex);
					firstLeft = false;
				}else{
					if(stack.empty()){
						firstLeft = true;
					}
				}
			}
		}
		
		StringBuilder builder = new StringBuilder(expression);
		Iterator<Integer> iterator = queue.iterator();
		  while(iterator.hasNext()){
		    int id = iterator.next();
		    builder = builder.deleteCharAt(id );
		  }
		return builder.toString();
	} 
	
	static String reverse(String expression){
		char[] array = expression.toCharArray();
		int start = 0;
		int end = array.length-1;
		while(start<end){
			char temp = array[start];
			array[start] = array[end];
			array[end] = temp;
			if(array[start] == '('){
				array[start] = ')';
			}else if(array[start] == ')'){
				array[start] = '(';
			}
			if(array[end] == '('){
				array[end] = ')';
			}else if(array[end] == ')'){
				array[end] = '(';
			}
			start++;
			end--;
		}
		if(expression.length()%2 != 0){
			if(array[start] == ')'){
				array[start] = '(';
			}else if(array[end] == '('){
				array[start] = ')';
			}
		}
		
		return new String(array);
	}
	
	public static void main(String[] args ){
		String expression1 = "(AB)C((DE)F)/S";
		String expression2 = "(AB)CDE/R";
		String expression3 = "A(BC)/RSR";
		String expression4 = "(AB)C(D((E)FG)H)/S";
		System.out.println("result for 1 is " + expressionTree(expression1));
		System.out.println("result for 2 is " + expressionTree(expression2));
		System.out.println("result for 3 is " + expressionTree(expression3));
		System.out.println("result for 4 is " + expressionTree(expression4));
	}
}
