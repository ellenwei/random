package interview;

public class DeleteNode {
	public static class Node{
		Node next;
		int val;
		
		public Node(int val){
			this.val = val;
		}
	}
	
	
	public static Node deleteEven(Node root){
		Node temp = root;
		if(root.next != null){
			helper(temp, temp.next.next);
		}		
		return root;
	}
	
	public static void helper(Node first, Node second){
		first.next = second;
		if(second.next == null){
			return;
		}
		helper(second, second.next.next);
		
	}
	
	public static void printResult(Node result){
		while(result!=null){
			System.out.println(result.val);
			result = result.next;
		}
	}
	
	public static void main(String[] argv){
		Node first = new Node(1);
		Node second = new Node(2);
		Node third = new Node(3);
		Node fourth = new Node(4);
		Node fifth = new Node(5);
		//first.next = null;
		first.next = second;
		second.next = third;
		third.next = fourth;
		fourth.next = fifth;
		Node result = deleteEven(first);
		
		printResult(result);
		
	}
}
