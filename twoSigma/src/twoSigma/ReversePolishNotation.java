package twoSigma;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
	
interface Token{
	public void execute(Stack<Double> stack);
}

class Operand implements Token{
	private double value;
	
	public Operand(double value){
		this.value = value;
	}
	
	@Override
	public void execute(Stack<Double> stack){
		stack.push(this.value);
	}
	
}

interface Operator extends Token{	}

class Add implements Operator{
	@Override
	public void execute(Stack<Double> stack){
		Double num2 = stack.pop();
		Double num1 = stack.pop();
		stack.push(num1+num2);
	}
}

class Subtract implements Operator{
	@Override
	public void execute(Stack<Double> stack){
		Double num2 = stack.pop();
		Double num1 = stack.pop();
		stack.push(num1-num2);
	}
}

class Multiply implements Operator{
	@Override
	public void execute(Stack<Double> stack){
		Double num2 = stack.pop();
		Double num1 = stack.pop();
		stack.push(num1*num2);
	}
}

class Divide implements Operator{
	public void execute(Stack<Double> stack){
		Double num2 = stack.pop();
		Double num1 = stack.pop();
		stack.push(num1/num2);
	}
}

public class ReversePolishNotation{
	private Map<String, Operator> map;
	private Stack<Double> stack;
	
	public ReversePolishNotation(){
		map = new HashMap<>();
		map.put("+", new Add());
		map.put("-", new Subtract());
		map.put("*", new Multiply());
		map.put("/", new Divide());
	}
	
	private Token getToken(String token){
		Token result = map.get(token);
		if(result == null){
			result = new Operand(Double.valueOf(token));
		}
		return result;
	}
	
	public double calculate(String input){
		this.stack = new Stack<Double>();
		String[] inputs = input.split("\\s");
		for(String str:inputs){
			Token token = getToken(str);
			token.execute(this.stack);
		}
		return stack.pop();
	}
	
	public static void main(String[] argv){
	ReversePolishNotation  cal = new ReversePolishNotation ();
	// (1 + 2) * 4 + (4 - 2) == 14
	System.out.println("1 2 + 4 * 4 2 - + = " 
			   + cal.calculate("1 2 + 4 * 4 2 - +"));	
	// (1 + 4) * (3 + 7) / 5 == 10
	System.out.println("1 4 + 3 7 + * 5 / = " 
			   + cal.calculate("1 4 + 3 7 + * 5 /"));	
	// 10 + 2 == 12
	System.out.println("10 2 + = " 
			   + cal.calculate("10 2 +"));	
	// 10 / 2 == 5
	System.out.println("10 2 / = "
			   + cal.calculate("10 2 /"));	
	}
}

