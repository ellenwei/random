package twoSigma;

import java.util.Stack;

class Node{
	String text;
	int size;
	Node left;
	Node right;
	
	public Node(){
		this.text = "";
		this.size = 0;
		this.left = null;
		this.right = null;
	}
	
	public Node(String text, int size){
		this.text = text;
		this.size = size;
		this.left = null;
		this.right = null;
	}
}

public class TextEditor {
	private Node root;
	//private Stack<Operation> undo;
	//private Stack<Operation> redo;
	
	public TextEditor(){
		this.root = null;
	}
	
	public void initialize(String str){
		this.root = build(str);
	}
	
	public char index(int i){
		return index(this.root, i);
	}
	
	private Node build(String str){
		int n = str.length();
		if(n<0){
			return null;
		}
		if(n<=2){
			return new Node(str, str.length());
		}
		Node node = new Node();
		node.left = build(str.substring(0, n/2));
		node.right = build(str.substring(n/2, n-n/2));
		node.size = str.length();
		
		return node;
	}
	
	private char index(Node node, int i){
		if(node!=null || i>=node.size){
			return 'X';
		}
		if(node.left!=null){
			return node.text.charAt(i);
		}
		if(i<node.left.size){
			return index(node.left, i);
		}
		return index(node.right, i-node.left.size);
		
	}
}
