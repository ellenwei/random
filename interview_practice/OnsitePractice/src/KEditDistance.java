import java.util.LinkedList;
import java.util.List;


public class KEditDistance {
	
	static class TrieNode{
		TrieNode[] children = new TrieNode[26];
		boolean isLeaf = false;;
	}
	
	public static TrieNode buildTrie(String[] words){
		TrieNode root = new TrieNode();
		for(String word:words){
			TrieNode p = root;
			for(char c:word.toCharArray()){
				if(p.children[c-'a'] == null){
					p.children[c-'a'] = new TrieNode();
				}
				p = p.children[c-'a'];
			}
			p.isLeaf = true;
		}
		return root;
	}
	
	/**
	 * Given a list of word and a target word, 
	 * output all the words for each the edit distance with the target no greater than k.
	   e.g. [abc, abd, abcd, adc], target "ac", k = 1,
	   output = [abc, adc]
	 * @param words
	 * @param target
	 * @param k
	 * @return
	 */
	public static List<String> KEditDistance(String[] words, String target, int k){
		List<String> result = new LinkedList<String>();
		if(words.length == 0 || words == null || target == null || target.length() == 0 || k<0){
			return result;
		}
		TrieNode root = buildTrie(words);
		int[] previous = new int[target.length()+1];
		for(int i=0; i<previous.length; i++){
			previous[i] = i;
		}
		helper("", target, k, previous, root, result);
		return result;
		
	}
	
	public static void helper(String current, String target, int k, int[] previous, TrieNode node, List<String>result){
		if(node.isLeaf){
			if(previous[target.length()]<=k){
				result.add(current);
			}else{
				return;
			}
		}
			for(int i=0; i<26; i++){
				if(node.children[i] == null){
					continue;
				}
				int[] currentDis = new int[target.length()+1];
				currentDis[0] = current.length()+1;
				for(int j=1; j<currentDis.length; j++){
					if(target.charAt(j-1) == (char)i+'a'){
						currentDis[j] = previous[j-1];
					}else{
						currentDis[j] = Math.min(Math.min(previous[j-1], currentDis[j-1]), previous[j])+1;
					}
				}
				helper(current+(char)(i+'a'), target, k, currentDis, node.children[i], result);
			}
	
	}
	
	public static void main(String[] argv){
		String[] input = new String[]{"abcd", "abc", "abd", "ad"};
	    String target = "ac";
	    int k = 1;   
	    List<String> result = KEditDistance(input, target, k);
	    System.out.println("result is: ");
	    for (String s : result) {
	      System.out.println(s);
	    }
	}
}
