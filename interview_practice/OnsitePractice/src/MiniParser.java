import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
	 * 实现一个mini parser, 输入是以下格式的string:"324" or"[123,456,[788,799,833],[[]],10,[]]"
	   要求输出:324 or [123,456,[788,799,833],[[]],10,[]].
       也就是将字符串转换成对应的格式的数据.
       输入一个数组的字符串, 要返回一个数组, 里面每一个元素是要么一个整数, 要么是一个数组.
       但是注意数组可以多层嵌套. 
	 */
public class MiniParser {
	int value;
	boolean isNumber;
	List<MiniParser> list;
	
	public MiniParser(int value){
		this.isNumber = true;
		this.value = value;
	}
	
	public MiniParser(){
		isNumber = false;
		list = new LinkedList<>();
	}
	
	public void add(MiniParser node){
		this.list.add(node);
	}
	
	/**
 	* If encounters '[', 
    push current NestedInteger to stack and start a new one.
    If encounters ']', 
	end current NestedInteger and pop a NestedInteger from stack to continue.
    If encounters ',', 
	append a new number to curr NestedInteger, if this comma is not right after a brackets.
    Update index l and r, 
	where l shall point to the start of a integer substring, while r shall points to the end+1 of substring.
	 * @param input
	 * @return
	 */
	
	public MiniParser miniParser2(String input){
		if(input.length() == 0 || input == null){
			return null;
		}
		if(input.charAt(0) != '['){
			return new MiniParser(Integer.parseInt(input));
		}
		int left = 0;
		Stack<MiniParser> stack = new Stack<>();
		MiniParser parser = null;
		for(int right = 0; right<input.length(); right++){
			char c = input.charAt(right);
			if(c == '['){
				if (parser != null){
	                stack.push(parser);
	            }
	            parser = new MiniParser();
	            left = right+1;
			}else if(c == ']'){
				String num = input.substring(left,right);
	            if (num.length()>0){
	                parser.add(new MiniParser(Integer.parseInt(num)));
	            }
	            if (!stack.isEmpty()){
	                MiniParser pop = stack.pop();
	                pop.add(parser);
	                parser = pop;
	            }
	            left = right+1;
			}else if(c == ','){
				if (input.charAt(right-1) != ']') {
	                String num = input.substring(left,right);
	                parser.add(new MiniParser(Integer.parseInt(num)));
	            }
	            left = right+1;
			}	
		}
		return parser;
	}
	
	public MiniParser miniParser1(String input){
		if(input.length() == 0 || input == null){
			return null;
		}
		if(input.charAt(0) != '['){
			return new MiniParser(Integer.parseInt(input));
		}
		int left = 0;
		Stack<MiniParser> stack = new Stack<>();
		MiniParser parser = null;
		for(int right = 0; right<input.length(); right++){
			char c = input.charAt(right);
			if(c == '['){
				if (parser != null){
	                stack.push(parser);
	            }
	            parser = new MiniParser();
	            left = right+1;
			}else if(c == ']'){
				String num = input.substring(left,right);
	            if (num.length()>0){
	                parser.add(new MiniParser(Integer.parseInt(num)));
	            }
	            if (!stack.isEmpty()){
	                MiniParser pop = stack.pop();
	                pop.add(parser);
	                parser = pop;
	            }
	            left = right+1;
			}else if(c == ','){
				if (input.charAt(right-1) != ']') {
	                String num = input.substring(left,right);
	                parser.add(new MiniParser(Integer.parseInt(num)));
	            }
	            left = right+1;
			}			
		}
		return parser;
	}
	
	public  String toString(){
		if(this.isNumber){
			return this.value+"";
		}else{
			return this.list.toString();
		}	
	}
	
	public static void main(String[] args){
		MiniParser nestedIntList = new MiniParser();
		String input = "[123,456,[788,799,833],[[]],10,[]]";
		String input1 = "324";
		MiniParser result = nestedIntList.miniParser2(input);
		MiniParser result1 = nestedIntList.miniParser2(input1);
	    System.out.println(result.toString());
	    System.out.println(result1.toString());
	}
}
