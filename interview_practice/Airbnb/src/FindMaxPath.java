import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class FindMaxPath {
	public static void main(String[] args){
		String[] dict={"abs","abc","dd","bb"};
		char[][] mat={{'a','b','c'},{'d','d','d'},{'b','b','d'}};
		System.out.println("result: "+findmaxPath(dict,mat));
	}

	private static int max=0;
	private static TrieNode root=new TrieNode();

	public static int findmaxPath(String[] words, char[][] mat){
        for(int i=0;i<words.length;i++){
                insertT(words[i]);
        }
        int range=mat.length*mat[0].length;
        HashSet<Integer> set=new HashSet<Integer>();
        for(int i=0;i<range;i++){
                pathfind(set,root,mat,i,0);
        }
        return max;
	}

	public static void pathfind(Set<Integer> set, TrieNode node, char[][] mat, int pos, int curcount){
		if(node.isLeaf){
            max=Math.max(max,curcount+1);
            node.isLeaf=false;
            pathfind(set,root,mat,pos,curcount+1);
            node.isLeaf=true;
		}
		int x=pos/mat[0].length;
		int y=pos%mat[0].length;
		if(node.children[mat[x][y]-'a']!=null){
            set.add(pos);
            for(Integer connect:connected(pos,mat)){
                    if(!set.contains(connect)){
                            pathfind(set,node.children[mat[x][y]-'a'],mat,connect,curcount);
                    }
            }
            set.remove(pos);
		}
		return;
	}

	public static List<Integer> connected(int pos, char[][] mat){
		int m=mat.length;
		int n=mat[0].length;
		int x=pos/n;
		int y=pos%n;
		List<Integer> res=new ArrayList<Integer>();
		for(int i=-1;i<=1;i+=2){
            if(x+i>=0&&x+i<m){
                    res.add((x+i)*n+y);
            }
            if(y+i>=0&&y+i<n){
                    res.add(x+y+i);
            }
		}
		return res;
	}

	public static void insertT(String s){
		TrieNode cur=root;
		for(int i=0;i<s.length();i++){
            if(cur.children[s.charAt(i)-'a']==null){
                     cur.children[s.charAt(i)-'a']=new TrieNode();
            }
            cur=cur.children[s.charAt(i)-'a'];
            if(i==s.length()-1){
                    cur.isLeaf=true;
            }
		}
	}
}
