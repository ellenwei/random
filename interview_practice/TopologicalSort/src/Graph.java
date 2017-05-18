import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Graph {
	int vertex;
	List<Integer>[] adjcent;
	
	@SuppressWarnings("unchecked")
	public Graph(int vertex){
		this.vertex = vertex;
		this.adjcent = new LinkedList[vertex];
		for (int i=0; i<vertex; i++){
			this.adjcent[i] = new LinkedList<Integer>();
		}
	}
	
	public void addEdge(int vertex, int targetVertex){
		this.adjcent[vertex].add(targetVertex);
	}
	
	@SuppressWarnings("rawtypes")
	private void topologicalSortHelper(int index, boolean[] check, Stack<Integer> stack){
		check[index] = true;
		Iterator it = this.adjcent[index].iterator();
		Integer currentVertex;
		while(it.hasNext()){
			currentVertex = (Integer) it.next();
			if (!check[currentVertex]){
				topologicalSortHelper(currentVertex, check, stack);
			}
		}
		stack.push(index);
	}
	
	public void topologicalSort(){
		Stack<Integer> stack = new Stack<>();
		boolean[] check = new boolean[this.vertex];
		
		for (int i=0; i<this.vertex; i++){
			check[i] = false;
		}
		
		for (int i=0; i<this.vertex; i++){
			if (!check[i]){
				topologicalSortHelper(i, check, stack);
			}
		}
		
		while (!stack.isEmpty()){
			System.out.print(stack.pop() + " ");
		}
	}
}
