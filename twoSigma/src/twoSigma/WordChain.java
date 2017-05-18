package twoSigma;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WordChain {
	
	public static int findLongestChain(String[] words){
		if(words.length == 0 || words == null){
			return 0;
		}
		int longestChain = 0;
		Arrays.sort(words, new Comparator<String>(){
			@Override
			public int compare(String s1, String s2){
				return s1.length()-s2.length();
			}
		});
		Map<String, Integer>map = new HashMap<String, Integer>();
		for(String word:words){
			if(map.containsKey(word)){
				continue;
			}
			map.put(word, 1);
			for(int i=0; i<word.length(); i++){
				StringBuilder builder = new StringBuilder(word);
				builder.deleteCharAt(i);
				String string = builder.toString();
				if(map.containsKey(string)&& map.get(string)+1>map.get(word)){
					map.put(word, map.get(string)+1);
				}
			}
			if(map.get(word)>longestChain){
				longestChain = map.get(word);
			}
		}
		return longestChain;
	}
	
	public static void main(String[] argv){
		Scanner scan = new Scanner(System.in);
		int count = Integer.parseInt(scan.nextLine());
		String[] words = new String[count];
		for(int i=0; i<count; i++){
			words[i]=scan.nextLine();
		}
		System.out.println(findLongestChain(words));
	}
}
