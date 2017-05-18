
public class NumberString {

	static String numberString(String input){
		if (input.length() == 2){
			int first = Character.getNumericValue(input.charAt(0));
			int second = Character.getNumericValue(input.charAt(1));
			return ""+Math.max(first, second);
		}
		for (int i=0; i<input.length()-2; i++){
			int firstInt = Character.getNumericValue(input.charAt(i));
			int secondInt = Character.getNumericValue(input.charAt(i+1));
			int thirdInt = Character.getNumericValue(input.charAt(i+2));
			if (firstInt > secondInt && secondInt > thirdInt){
				return input.substring(0, i+1)+input.substring(i+2,input.length());
			}
		}
		int first = Character.getNumericValue(input.charAt(input.length()-2));
		int second = Character.getNumericValue(input.charAt(input.length()-1));
		return input.substring(0, input.length()-2)+Math.max(first, second);
		
	}
	
	public static void main(String[] args){
		System.out.println(numberString("7664351"));
		
	}
}
