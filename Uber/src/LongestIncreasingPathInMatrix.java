import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class LongestIncreasingPathInMatrix {
	public String alienOrder(String[] words) {
		Map<Character, HashSet<Character>> map = new HashMap<>();
        int[] arr = new int[26];
        for (int i=0;i<words.length; i++){
            char[] temp = words[i].toCharArray();
            for (char c:temp){
                if (!map.containsKey(c)){
                    map.put(c, new HashSet<Character>());
                }
            }
            if (i == 0){
                continue;
            }
            if (words[i-1].length() > words[i].length() && words[i].equals(words[i-1].substring(0, words[i].length()))) {
                return "";
            }
            int len = Math.min(words[i-1].length(), words[i].length());
            int p = 0;
            while(p<len && words[i-1].charAt(p) == words[i].charAt(p)){
                p++;
            }
            if (p == len){
                continue;
            }
            
            char c1 = words[i-1].charAt(p);
            char c2 = words[i].charAt(p);
            if (map.get(c1).add(c2)){
                arr[c2-'a']++;
            }
        }
        Queue<Character> queue = new LinkedList<>();
        for (int i=0; i<arr.length; i++){
            char c = (char)(i+'a');
            if (arr[i]==0 && map.containsKey(c)){
                queue.offer(c);
            }
        }
        StringBuilder builder = new StringBuilder();
        while (!queue.isEmpty()){
            char current = queue.poll();
            builder.append(current);
            for (Object obj:map.get(current)){
                char c = (char)obj;
                arr[c-'a']--;
                if (arr[c-'a'] == 0){
                    queue.offer(c);
                }
            }
        }
        for (int i=0; i<arr.length; i++){
            if (arr[i]>0){
                return "";
            }
        }
        return builder.toString();
	}
}
