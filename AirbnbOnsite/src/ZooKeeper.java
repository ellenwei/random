import java.util.LinkedList;
import java.util.List;

public class ZooKeeper {
	static class Node{
		String value;
		List<Node> children = new LinkedList<>();
		
		public Node(String path){
			this.value = path;
		}
	}
	
	Node root = new Node("0");
	
	public ZooKeeper(){
		
	}
	
	void create(String path){
		String[] pathArray = path.split("/");
		int i=0;
		while(root!=null && i<pathArray.length){
			boolean isMatch = false;
			for(Node node:root.children){
				if(node.value == pathArray[i]){
					root = node;
					i++;
					isMatch = true;
					break;
				}
			}
			if(!isMatch){
				Node node = new Node(pathArray[i]);
				System.out.println("added val is "+pathArray[i]);
				root.children.add(node);
				root = node;
				i++;
			}
			
		}		
	}
	
	String get(String path){
		String[] pathArray = path.split("/");
		int i=0;
		while(root!=null && i<pathArray.length){
			if(root.value == pathArray[i]){
				i++;
				Node temp = new Node(pathArray[i]);
				if(root.children.contains(temp)){
					root = temp;
					i++;
				}else{
					throw new RuntimeException();
				}
			}
			if(i == pathArray.length-1){
				break;
			}
		}
		return root.value;
	}
	
	void set(String path, String value){
		Node node = new Node(value);
		String[] pathArray = path.split("/");
		int i=0;
		while(root!= null&& i<pathArray.length){
			for(Node temp:root.children){
				System.out.print(temp.value+" ");
				if(temp.value == pathArray[i]){
					temp = node;
					i++;
					break;
				}
			}
			throw new RuntimeException();
		}
		root = node;
	}
	
	public static void main(String[] argv){
		ZooKeeper keeper = new ZooKeeper();
		keeper.create("animal/feline");
		System.out.println(keeper.get("animal"));
		keeper.set("animal", "dog");
		System.out.println(keeper.get("animal"));
	}
}
