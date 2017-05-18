
public class TrieNode {
	TrieNode[] children;
    boolean isLeaf;
    String word;
     
    public TrieNode() {
      children = new TrieNode[26];
       
    }
}
