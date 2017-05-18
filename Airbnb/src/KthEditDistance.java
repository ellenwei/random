import java.util.LinkedList;
import java.util.List;

public class KthEditDistance {
	public static List<String> getKthEditDistance(String[] words, String target, int k){
		List<String> result = new LinkedList<String>();
		if(words.length == 0 || words == null || target == null || target.length() == 0 || k<0){
			return result;
		}
		Trie trie = new Trie();
		for(String word: words){
			trie.add(word);
		}
		TrieNode root = trie.root;
		int[] prev = new int[target.length()+1];
		for(int i=0; i<prev.length; i++){
			prev[i] = i;
		}
		helper("", target, k, root, prev, result);
		return result;
	}
	
	public static void helper(String curr, String target, int k, TrieNode root, int[] prevDist, List<String> result){
		if (root.isLeaf) {
		      if (prevDist[target.length()] <= k) {
		        result.add(curr);
		      } else {
		        return;
		      }
		    }
		     
		    for (int i = 0; i < 26; i++) {
		      if (root.children[i] == null) {
		        continue;
		      }
		       
		      int[] currDist = new int[target.length() + 1];
		      currDist[0] = curr.length() + 1;
		      for (int j = 1; j < prevDist.length; j++) {
		        if (target.charAt(j - 1) == (char) (i + 'a')) {
		          currDist[j] = prevDist[j - 1];
		        } else {
		        	//replace, insert, delete
		          currDist[j] = Math.min(Math.min(prevDist[j - 1], prevDist[j]), 
		                                 currDist[j - 1]) + 1;
		        }
		      }
		       
		      helper(curr + (char) (i + 'a'), target, k, 
		         root.children[i], currDist, result);
		    }
	}
	
	public static void main(String[] args){	
	    String[] input = new String[]{"abcd", "abc", "abd", "ad"};
	    String target = "ac";
	    int k = 1;   
	    List<String> result = getKthEditDistance(input, target, k);
	    System.out.println("result is: ");
	    for (String s : result) {
	      System.out.println(s);
	    }
	}
}
