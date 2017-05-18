package twoSigma;

public class StreamPalindrome {
	public final static int c = 256;
	public final static int p = 103;
		
	public static void isPalindrome(String str){
		if(str.length() == 0 || str == null){
			return;
		}
		System.out.println(str.charAt(0)+" true");
		if(str.length() == 1){
			return;
		}
		int firstR = str.charAt(0)%p;
		int second = str.charAt(1)%p;	
		int hash = 1, j;		
		for(int i=1; i<str.length(); i++){
			if(firstR == second){
				for(j = 0; j<i/2; j++){
					if(str.charAt(j) != str.charAt(i-j)){
						break;
					}
				}
				if(j == i/2){
					System.out.println(str.charAt(i)+" true");
				}else{
					System.out.println(str.charAt(i)+" false");
				}			
			}else{
				System.out.println(str.charAt(i)+" false");
			}		
			if(i != str.length()-1){	
				if(i%2 != 0){
					second = (c*(second+p-str.charAt((i+1)/2)*hash)%p+str.charAt(i+1))%p;
				}else{
					hash = (hash*c)%p;
					second = (second*c+str.charAt(i+1))%p;
					firstR = (firstR + hash*str.charAt(i/2))%p;
				}
			}		
		}
	}
	
	public static void main(String[] argv){
		String input = "aabaacaabaa";
		String input1 = "abab";
		String input2 = "aaa";
		String input3 = "a";
		String input4 = "";
		isPalindrome(input);
		System.out.println("-------");
		isPalindrome(input1);
		System.out.println("-------");
		isPalindrome(input2);
		System.out.println("-------");
		isPalindrome(input3);
		System.out.println("-------");
		isPalindrome(input4);
		
	}
}
