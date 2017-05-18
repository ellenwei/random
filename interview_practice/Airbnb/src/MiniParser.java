
public class MiniParser {
	/**
	 * 实现一个mini parser, 输入是以下格式的string:"324" or"[123,456,[788,799,833],[[]],10,[]]"
	   要求输出:324 or [123,456,[788,799,833],[[]],10,[]].
       也就是将字符串转换成对应的格式的数据.
       输入一个数组的字符串, 要返回一个数组, 里面每一个元素是要么一个整数, 要么是一个数组.
       但是注意数组可以多层嵌套. 

	 * @param args
	 */
	public static void main(String[] args){
		NestedIntList nestedIntList = new NestedIntList();
		String input = "[123,456,[788,799,833],[[]],10,[]]";
		String input1 = "324";
		NestedIntList result = nestedIntList.miniParser(input);
		NestedIntList result1 = nestedIntList.miniParser(input1);
	    System.out.println(result.toString());
	    System.out.println(result1.toString());
	}
}
