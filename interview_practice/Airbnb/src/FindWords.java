import java.util.LinkedList;
import java.util.List;

public class FindWords {
	
	static class TrieElement{
		TrieElement[] children = new TrieElement[26];
		String word;
	}
	
	public static TrieElement buildTrie(String[] words){
		TrieElement root = new TrieElement();
		for(String word:words){
			TrieElement p = root;
			for(char c:word.toCharArray()){
				if(p.children[c-'a'] == null){
					p.children[c-'a'] = new TrieElement();
				}
				p = p.children[c-'a'];
			}
			p.word = word;
		}
		return root;
	}
	/**
	 * Given a 2D board and a list of words from the dictionary, 
	 * find all words in the board.
	Each word must be constructed from letters of sequentially adjacent cell, 
	where "adjacent" cells are those horizontally or vertically neighboring. 
	The same letter cell may not be used more than once in a word.

	For example,
	Given words = ["oath","pea","eat","rain"] and board =

	[
	  ['o','a','a','n'],
	  ['e','t','a','e'],
	  ['i','h','k','r'],
	  ['i','f','l','v']
	]
	Return ["eat","oath"].
	 */
		public static List<String> findWords(char[][] board, String[] words) {
			List<String>result = new LinkedList<>();
			TrieElement root = buildTrie(words);
			for(int i=0; i<board.length; i++){
				for(int j=0; j<board[0].length; j++){
					dfs(board, i, j, root, result);
				}
			}
			return result;
		}
		
		public static void dfs(char[][]board, int i, int j, TrieElement p, List<String> result){
//			char c = board[i][j];
//			if(c == '#' || root.children[c-'a'] == null){
//				return;
//			}
//			root = root.children[c-'a'];
//			if(root.word!=null){
//				result.add(root.word);
//				root.word = null;
//			}
//			board[i][j] = '#';
//			if(i>0){
//				dfs(board, i-1, j, root, result);
//			}
//			if(j>0){
//				dfs(board, i, j-1, root, result);
//			}
//			if(i<board.length-1){
//				dfs(board, i+1, j, root, result);
//			}
//			if(j<board[0].length-1){
//				dfs(board, i, j+1, root, result);
//			}
			char c = board[i][j];
	        if (c == '#' || p.children[c-'a'] == null  ){
	            return;
	        }
	        p = p.children[c-'a'];
	        if (p.word!=null){
	            result.add(p.word);
	            p.word = null;
	        }
	        board[i][j] = '#';
	        if(i>0){
	            dfs(board, i-1, j, p, result);
	        }
	        if (j>0){
	            dfs(board, i, j-1, p, result);
	        }
	        if (i<board.length-1){
	            dfs(board, i+1, j, p, result);
	        }
	        if (j<board[0].length-1){
	            dfs(board, i, j+1, p, result);
	        }
	        board[i][j] = c;
		}
		
		public static void main(String[] argv){
			char[][] board = {{'o','a','a','n'},
							  {'e','t','a','e'},
							  {'i','h','k','r'},
							  {'i','f','l','v'}};
			String[] words = {"oath","pea","eat","rain"};
			for(String word:findWords(board, words)){
				System.out.print(word+" ");
			}
		}
}
