package twoSigma;

public class FriendCircle {
	public static int findCircle(String[] friends){
		UnionFind unionFind = new UnionFind(friends.length);
		for(int i=0; i<friends.length; i++){
			for(int j=0; j<i; j++){
				if(friends[i].charAt(j) == 'Y'){
					unionFind.union(i, j);
				}
			}
		}
		return unionFind.count;
	}
	
	public static class UnionFind{
		int[] index;
		int count;
		
		public UnionFind(int count){
			this.index = new int[count];
			this.count = count;
			for(int i=0; i<count; i++){
				index[i] = i;
			}
		}
		
		public int findId(int i){
			return index[i];
		}
		
		public boolean isConnected(int i, int j){
			return findId(i)==findId(j);
		}
		
		public boolean union(int one, int two){
			if(!isConnected(one,two)){
				for(int i=0; i<index.length; i++){
					if(index[i] == findId(two)){
						index[i] = findId(one);
					}
				}
				count--;
				return true;
			}
			return false;
		}
		
	}
	
	public static void main(String[] argv){
		String[] input  =new String[]{"YNYNNN","NYNYNN","YNYNNN","NYNYNN","NNNNYY","NNNNYY"};
		String[] input1 = new String[]{"YYNN","YYYN","NYYN","NNNY"};
		String[] input2 = new String[]{};
		int count = findCircle(input1);
		System.out.println(count);
	}
}
