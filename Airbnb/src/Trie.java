
public class Trie {
	TrieNode root;
    
    public Trie() {
      root = new TrieNode();
    }
     
    // Add a word into trie
    public void add(String s) {
      if (s == null || s.length() == 0) {
        return;
      }
       
      TrieNode p = root;
      for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        if (p.children[c - 'a'] == null) {
          p.children[c - 'a'] = new TrieNode();
        }
         
        if (i == s.length() - 1) {
          p.children[c - 'a'].isLeaf = true;
        }
         
        p = p.children[c - 'a'];
      }
      p.word = s;
    }
}
