package huawei;

//import java.util.Collections;
//import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Huawei {
	public static void main(String[] args){
//		@SuppressWarnings("resource")
//		Scanner scanner = new Scanner(System.in);
//		char[] input = scanner.nextLine().toCharArray();
//		List<Integer> number = new LinkedList<>();
//		List<Character> capital = new LinkedList<>();
//		List<Character> character = new LinkedList<>();
//		for (int i=0; i<input.length; i++){
//			if (input[i] <= 90 && input[i] >= 65){
//				capital.add(input[i]);
//			}else if(input[i] <= 57 && input[i] >= 48){
//				number.add(Integer.parseInt(input[i]+""));
//			}else{
//				character.add(input[i]);
//			}
//		}
//		Collections.sort(capital, new Comparator<Character>() {
//	        @Override
//	        public int compare(Character s1, Character s2) {
//	            return s1.compareTo(s2);
//	        }
//	    });
//		Collections.sort(character, new Comparator<Character>() {
//	        @Override
//	        public int compare(Character s1, Character s2) {
//	            return s1.compareTo(s2);
//	        }
//	    });
//		Collections.sort(number, new Comparator<Integer>() {
//	        @Override
//	        public int compare(Integer s1, Integer s2) {
//	            return s1.compareTo(s2);
//	        }
//	    });
//		capital.addAll(character);
//		StringBuilder builder = new StringBuilder();
//		for (int i=0; i<number.size(); i++){
//			builder.append(number.get(i));
//		}
//		for (int i=0; i<capital.size(); i++){
//			builder.append(capital.get(i));
//		}
//		System.out.println(builder.toString());
//		@SuppressWarnings("resource")
//		Scanner scanner = new Scanner(System.in);
//		String[] input = scanner.nextLine().split(" ");
//		List<Integer> builder1 = new LinkedList<Integer>();
//		List<Integer> builder2 = new LinkedList<Integer>();
//		for(int i=0; i<input.length; i++){
//			//System.out.println("i "+i);
//			if (i%2==0){
//				builder1.add(Integer.parseInt(input[i]));
//			}else{
//				builder2.add(Integer.parseInt(input[i]));
//			}		
//		}
//		// Sorting
//		Collections.sort(builder1, new Comparator<Integer>() {
//			@Override
//			public int compare(Integer o1, Integer o2) {
//				// TODO Auto-generated method stub
//				return o1.compareTo(o2);
//			}
//		});
//		Collections.sort(builder2, new Comparator<Integer>() {
//			@Override
//			public int compare(Integer o1, Integer o2) {
//				// TODO Auto-generated method stub
//				return o2.compareTo(o1);
//			}
//		});
//		int trackOdd = 0;
//		int trackEven = 0;
//		StringBuilder builder = new StringBuilder();
//		boolean track = true;
//		while(trackOdd<builder1.size() && trackEven<builder2.size()){
//			if(track){
//				builder.append(builder1.get(trackOdd)+" ");
//				track = false;
//				trackOdd++;
//			}else{
//				builder.append(builder2.get(trackEven)+" ");
//				track = true;
//				trackEven++;
//			}
//		}
//		if(track){
//			for (int i=trackOdd; i<builder1.size(); i++){
//				builder.append(builder1.get(i)+" ");
//			}
//			System.out.println(builder.toString());
//		}else{
//			for (int i=trackEven; i<builder2.size(); i++){
//				builder.append(builder2.get(i)+" ");
//			}
//			System.out.println(builder.toString());
//		}
//		
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		int check = Integer.parseInt(scanner.nextLine());
		int k=1;
		List<String> input = new LinkedList<>();
		while(scanner.hasNext() && k<check*2){
			input.add(scanner.nextLine());
			k++;
		}
		input.add(scanner.nextLine());
		for (int i=0; i<input.size()-1; i++){
			String array1 = input.get(i);
			String array2 = input.get(i+1);
			System.out.println(calculate(array1, array2));
			i++;
		}
	}
	
	static int calculate(String array1, String array2){		
		if(array1.equals(array2)){
			return 0;
		}
		int m = array1.length();
		int n = array2.length();
		if (m == n){
			int count = 0;
			for (int i=0; i<m; i++){
				if (array1.charAt(i) != array2.charAt(i)){
					count++;
				}
			}
			if(count == m){
				return 4+n;
			}
		}
		return 2+(2+m/3)*3;
		
	}
}


//Scanner cin = new Scanner(System.in);
//int a, b;
//while (cin.hasNext()){
//	a = cin.nextInt(); b = cin.nextInt();
//	System.out.println(a + b);
//}