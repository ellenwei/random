
public class EditDistance {
	/**
	 * Given two words word1 and word2, 
	 * find the minimum number of steps required to convert word1 to word2. 
	 * (each operation is counted as 1 step.)

You have the following 3 operations permitted on a word:

a) Insert a character
b) Delete a character
c) Replace a character
	 * @param word1
	 * @param word2
	 * @return
	 */
	public static int editDistance(String word1, String word2){
		int[][]dp = new int[word1.length()+1][word2.length()+1];
		for(int i=0; i<=word1.length(); i++){
			dp[i][0] = i;
		}
		for(int i=0; i<=word2.length(); i++){
			dp[0][i] = i;
		}
		for(int i=1; i<=word1.length(); i++){
			for(int j=1; j<=word2.length(); j++){
				if(word1.charAt(i-1) == word2.charAt(j-1)){
					dp[i][j] = dp[i-1][j-1];
				}else{
					// dp[i-1][j-1] : replace word1(i) with word2(j), because word1(0, i-1) == word2(0, j-1);
	                // dp[i  ][j-1] : insert word(j)
	                // dp[i-1][j  ] : delete word(i), because word1(0, i-1) == word2(0, j)
					dp[i][j] = Math.min(Math.min(dp[i-1][j-1], dp[i][j-1]),dp[i-1][j])+1;
				}
			}
		}
		return dp[word1.length()][word2.length()];
	}
	
	public static void main(String[] argv){
		System.out.println(editDistance("", "a"));
		System.out.println(editDistance("ba", "a"));
		System.out.println(editDistance("abced", "abcead"));
		
	}
}
