
public class RecoverBST {
	
	public class TreeNode{
		int val;
		TreeNode left = null;
		TreeNode right = null;
		public TreeNode(int val){
			this.val = val;
		}
	}
	
	TreeNode firstNode;
	TreeNode secondNode;
	TreeNode prevNode;
	
	public void recoverTree(TreeNode root){
		prevNode = new TreeNode(Integer.MIN_VALUE);
		traverse(root);
		TreeNode temp = firstNode;
		firstNode = secondNode;
		secondNode = temp;
	}
	
	private void traverse(TreeNode root){
		if(root == null){
			return; 
		}
		traverse(root.left);
		if(firstNode == null && prevNode.val>=firstNode.val){
			firstNode = prevNode;
		}
		if(firstNode != null && prevNode.val>=firstNode.val){
			secondNode = prevNode;
		}
		root = prevNode;
		traverse(root.right);
	}
}
