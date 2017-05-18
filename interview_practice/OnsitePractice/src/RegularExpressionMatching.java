
public class RegularExpressionMatching {
	/**
	 * '.' Matches any single character.
	   '*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "a*") → true
isMatch("aa", ".*") → true
isMatch("ab", ".*") → true
isMatch("aab", "c*a*b") → true
	 * @param argv
	 */
	
	/**
     * 1, If p.charAt(j) == s.charAt(i) :  dp[i][j] = dp[i-1][j-1];
     * 2, If p.charAt(j) == '.' : dp[i][j] = dp[i-1][j-1];
     * 3, If p.charAt(j) == '*': 
     * here are two sub conditions:
     *          1   if p.charAt(j-1) != s.charAt(i) : dp[i][j] = dp[i][j-2]  
     *          //in this case, a* only counts as empty
     *          2   if p.charAt(i-1) == s.charAt(i) or p.charAt(i-1) == '.':
     *                         dp[i][j] = dp[i-1][j]    
     *                         //in this case, a* counts as multiple a 
     *                      or dp[i][j] = dp[i][j-1]   
     *                      // in this case, a* counts as single a
     *                      or dp[i][j] = dp[i][j-2]   
     *                      // in this case, a* counts as empty
     * */
	
	public static boolean isMatch(String s, String p) {
		if(s == null || p == null){
			return false;
		}
		boolean[][] map = new boolean[s.length()+1][p.length()+1];
		map[0][0] = true;
		for (int i=1; i<map[0].length; i++){
            if (p.charAt(i-1) == '*'){
                if (i>1 && map[0][i-2] == true){
                    map[0][i] = true;
                }
            }
        }
		for(int i=1; i<map.length; i++){
			for(int j=1; j<map[0].length; j++){
				if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.'){
					map[i][j] = map[i-1][j-1];
				}else if(p.charAt(j-1) == '*'){
					if (p.charAt(j-2) != s.charAt(i-1) && p.charAt(j-2) != '.'){
                        map[i][j] = map[i][j-2];
                    }else{
                        map[i][j] = map[i][j-2] || map[i][j-1] || map[i-1][j];
                    }
				}
			}
		}
		return map[s.length()][p.length()];
	}
	
	public static void main(String[] argv){
		System.out.println(isMatch("aa", "a"));
		System.out.println(isMatch("aa", "aa"));
		System.out.println(isMatch("aaa", "aa"));
		System.out.println(isMatch("aa", "a*"));
		System.out.println(isMatch("aa", ".*"));
		System.out.println(isMatch("ab", ".*"));
		System.out.println(isMatch("aab", "c*a*b"));
	}
}
