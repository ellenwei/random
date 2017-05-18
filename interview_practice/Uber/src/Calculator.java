import java.util.Stack;

public class Calculator {
	public int calculate(String input){
		int result = 0;
		int num = 0;
		int sign = 1;
		Stack<Integer> stack = new Stack<>();
		for(int i=0; i<input.length(); i++){
			char c = input.charAt(i);
			if(Character.isDigit(c)){
				num = num*10+(int)(c-'0');
			}else if(c == '-'){
				num = sign*num;
				result += num;
				num = 0;
				sign = -1;
			}else if(c == '+'){
				num = sign*num;
				result += num;
				num = 0;
				sign = +1;
			}else if(c == '('){
				stack.push(result);
				stack.push(sign);
				sign = 1;
				result = 0;
			}else if(c == ')'){
				result += sign*num;
				num = 0;
				result *= stack.pop();
				result += stack.pop();
			}
		}
		if(num != 0){
			result += sign*num;
		}
		return result;
	}
}
