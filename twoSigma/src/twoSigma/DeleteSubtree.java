package twoSigma;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class DeleteSubtree {
	public static class Node{
		int parentIndex;
		boolean valid;
		boolean visited;
		
		public Node(int parentIndex){
			this.parentIndex = parentIndex;
			this.valid = true;
			this.visited = false;
		}
		
	} 
	
	public static class ArrayTree{
		private List<Node> nodes = new LinkedList<Node>();
		private int size;
		
		public ArrayTree(List<Integer> nodes){
			for(int i:nodes){
				this.nodes.add(new Node(i));
			}
			this.size = this.nodes.size();
		}
		
		public void deleteNode(int index){
			//if already deleted
			if(!nodes.get(index).valid){
				return;
			}
			
			// reset valid nodes back to unvisited
			reset();
			
			nodes.get(index).valid = false;
			nodes.get(index).visited = true;
			size--;
			
			for(int i=0; i<nodes.size(); ++i){
				if(nodes.get(i).visited){
					continue;
				}
				explore(i);
			}
		}
		
		private boolean explore(int index){
			// if current is root or already visited
			if(nodes.get(index).parentIndex == -1 || nodes.get(index).visited){
				nodes.get(index).visited = true;
				return nodes.get(index).valid;
			}
			
			nodes.get(index).visited = true;
			
			boolean isParentValid = explore(nodes.get(index).parentIndex);
			// current vallidness depend on parent's validness
			if(nodes.get(index).valid != isParentValid){
				nodes.get(index).valid = isParentValid;
				size--;
			}
			return isParentValid;
		}
		
		private void reset(){
			for(Node node:nodes){
				if(node.valid){
					node.visited = false;
				}
			}
		}
		
		public void print(){
			System.out.println("Tree size: "+this.size);
			for(int i=0; i<nodes.size(); i++){
				System.out.println("Node index: "+i+";parent index: "+ nodes.get(i).parentIndex
						+", visited: "+nodes.get(i).visited+", valid: "+nodes.get(i).valid); 
			}
		}
	}
	
	public static void main(String[] argv){
		List<Integer> test = new LinkedList<Integer>(Arrays.asList(1,5, 5,2 ,2 ,-1,3));
		ArrayTree tree = new ArrayTree(test);
		tree.print();
		
		tree.deleteNode(3);
		tree.print();
		
		tree.deleteNode(6);
		tree.print();
		
		tree.deleteNode(2);
		tree.print();
	}
}
