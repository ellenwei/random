// you can also use imports, for example:
import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");




class Solution {
    
    public class FileNode {
	public int previousPathLength;
	public boolean containsImage;
	public List<FileNode> content = new ArrayList<>();
	public int spaceNumber;
	public String line;
	
	
	public FileNode(int previousPathLength, String line){
		containsImage = false;
		this.line = line;
		calculateSpace();
		this.previousPathLength = previousPathLength;
	}
	
	public boolean checkImage(){
		
		return this.line.toLowerCase().contains(".jpeg")||this.line.toLowerCase().contains(".gif")||this.line.toLowerCase().contains(".png");
	}
	
	private void calculateSpace(){
		int count = 0;
		for (char character : this.line.toCharArray()){
			if (character == ' '){
				count++;
			}
		}
		this.spaceNumber = count;
	}
	
	public void addContent(FileNode contentNode){
		this.content.add(contentNode);
	}

}
    
    public static int result=0;
    public static int solution(String S) {
        // write your code in Java SE 8
        String[] lines = S.split("\n");
		Stack<FileNode> nodeStack = new Stack<FileNode>();
		FileNode root = new FileNode(-2, "");
		root.spaceNumber = -1;
		nodeStack.push(root);		
		for (String line : lines){
			FileNode node = new FileNode(0, line);
			while(nodeStack.peek().spaceNumber>=node.spaceNumber){
				nodeStack.pop();
			}
			node.previousPathLength=1+nodeStack.peek().previousPathLength+nodeStack.peek().line.length()- nodeStack.peek().spaceNumber;
			if (node.checkImage()){
				nodeStack.peek().containsImage = true;
			}
			nodeStack.peek().content.add(node);
			nodeStack.push(node);			
		}
		traverse(root);
		return result%1000000007;
    }
    
    public static void traverse(FileNode node){
		if(node.checkImage())
			result+=1+node.previousPathLength+node.line.length()-node.spaceNumber;
		for(int i=0;i<node.content.size();i++){
			traverse(node.content.get(i));
		}
	}


	public static void main(String[] args) {
		String temp = "file.jpeg\ndir11\n dir12\n picture.jpeg\n dir121\n file1.txt\ndir2\n file2.gif";
		System.out.println(solution(temp));

	}

}
