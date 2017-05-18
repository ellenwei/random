
public class InformationMasking {
	static String emailMasking(String email){
		StringBuilder builder = new StringBuilder();
		builder.append("E:");
		builder.append(email.charAt(0));
		builder.append("*****");
		int indexAt = email.lastIndexOf("@")-1;
		builder.append(email.substring(indexAt));
		return builder.toString();
	}
	
	static String phoneMasking(String phone){
		StringBuilder builder = new StringBuilder();
		builder.append("P:");
		char[] phoneArray = phone.toCharArray();
		int num=0;
		for(char c:phoneArray){
			if(Character.isDigit(c)){
				num++;
			}
		}
		int countryCode = num-10;
		int current = 0;
		boolean extra = false;
		if(countryCode>0){
			builder.append("+");
			extra = true;
		}
			while(current<phoneArray.length && countryCode>0){
				if(Character.isDigit(phoneArray[current])){
					builder.append("*");
					countryCode--;		
				}
				current++;
			}
			if(extra){
				builder.append("-");
			}
						
		
		int count = 0;
		while(current<phoneArray.length && count<3){
			if(Character.isDigit(phoneArray[current])){
				builder.append("*");		
				count++;
			}
			
			current++;
		}
		builder.append("-");
		count = 0;
		while(current<phoneArray.length && count<3){
			if(Character.isDigit(phoneArray[current])){
				builder.append("*");
				count++;
			}		
			current++;
		}
		builder.append("-");
		count = 0;
		while(current<phoneArray.length && count<4){
			if(Character.isDigit(phoneArray[current])){
				builder.append(phoneArray[current]);
				count++;
			}		
			current++;
		}	
		return builder.toString();
	}
	
	public static void main(String[] args){
		String email = "ellenwei@gmail.com";
		System.out.println(emailMasking(email));
		String phone1 = "+1 (333) 444-5678";
		String phone2 = "+91 (333) 444-5678";
		String phone3 = "+111 (333) 444-5678";
		String phone4 = "333 444 5678";
		String phone5 = "(333) 444 5678";
		String phone6 = "+13334445678";
		System.out.println("Phone1 "+phoneMasking(phone1));
		System.out.println("Phone2 "+phoneMasking(phone2));
		System.out.println("Phone3 "+phoneMasking(phone3));
		System.out.println("Phone4 "+phoneMasking(phone4));
		System.out.println("Phone5 "+phoneMasking(phone5));
		System.out.println("Phone6 "+phoneMasking(phone6));
	}
}
