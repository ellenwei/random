public class MultiplyString {
	
	public static String multiply3(String num1, String num2){
		if(Integer.parseInt(num1) == 0 || Integer.parseInt(num2) == 0){
			return "0";
		}
		int m=num1.length();
		int n=num2.length();
		int mEnd=0;
		int nEnd=0;
		if(num1.charAt(0) == '-'){
			mEnd = 1;
		}
		if(num2.charAt(0) == '-'){
			nEnd = 1;
		}
		int[] resultArray = new int[m+n];
		for(int i=m-1; i>=mEnd; i--){
			for(int j=n-1; j>=nEnd; j--){
				int result = (num1.charAt(i)-'0')*(num2.charAt(j)-'0');
				int p1 = i+j;
				int p2 = i+j+1;
				int sum = resultArray[p2]+result;
				resultArray[p2] = sum%10;
				resultArray[p1] += sum/10;
			}
		}
		StringBuilder builder = new StringBuilder();
		int i=0;
		for(; i<resultArray.length; i++){
			if(builder.length() == 0 && resultArray[i] != 0){
				break;
			}
		}
		if((mEnd!= 0 && nEnd == 0) || (mEnd==0 && nEnd!=0)){
			builder.append('-');
		}
		for(; i<resultArray.length; i++){
			builder.append(resultArray[i]);
		}
		if(builder.length() == 0){
			return "0";
		}
		return builder.toString();
	}
	
	public static String multiply2(String num1, String num2) {
		int m=num1.length();
		int n=num2.length();
		int mEnd = 0;
		int nEnd = 0;
		if(num1.charAt(0) == '-'){
			
			mEnd = 1;
		}
		if(num2.charAt(0) == '-'){
			nEnd = 1;
		}
		int[] result=new int[m+n];
		for(int i=m-1; i>=mEnd; i--){
			for(int j=n-1; j>=nEnd; j--){
				int result1 = (num1.charAt(i)-'0')*(num2.charAt(j)-'0');
				int p1 = i+j;
				int p2 = i+j+1;
				int sum = result[p2]+result1;
				result[p2] = sum%10;
				result[p1] += sum/10;
			}
		}
		StringBuilder builder = new StringBuilder();
		int i=0;
		for(; i<result.length; i++){
			if(builder.length() == 0 && result[i]!=0){
				break;
			}
		}
		if((mEnd == 0 && nEnd!=0) || (nEnd==0 && mEnd!=0)){
			builder.append('-');
		}
		for(; i<result.length; i++){
			builder.append(result[i]);
		}
		if(builder.length() == 0){
			return "0";
		}
		return builder.toString();
	}
	
	public static String multiply1(String num1, String num2) {
		int m=num1.length();
        int n=num2.length();
        int mEnd = 0;
        int nEnd = 0;
        if(num1.charAt(0) == '-'){
        	mEnd = 1;
        }
        if(num2.charAt(0) == '-'){
        	nEnd = 1;
        }
        int[] temp = new int[m+n];
        for (int i=m-1; i>=mEnd; i--){
            for(int j=n-1; j>=nEnd; j--){
                int result = (num1.charAt(i)-'0')*(num2.charAt(j)-'0');
                int p1 = i+j;
                int p2 = i+j+1;
                int sum = temp[p2]+result;
                temp[p2] = sum%10;
                temp[p1]+=sum/10;
            }
        }
        StringBuilder builder = new StringBuilder();
        int i = 0;
        for(; i<temp.length; i++){
            if(!(builder.length() == 0 && i == 0)){
                break;
            }
        }
        if(mEnd!=0 || nEnd!= 0){
        	builder.append("-");
        }
        i++;
        for(; i<temp.length; i++){
        	builder.append(temp[i]);
        }
        if(builder.length() == 0){
            return "0";
        }else{
            return builder.toString();
        }
	}
	
	public static void main(String[] argv){
		String num1 = "200";
		String num2 = "0";
		System.out.println("The result of "+num1+" "+"multiply "+num2+" is "+multiply3(num1, num2));
	}
}
