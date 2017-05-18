public class InsertBST {
	public class TreeNode {
		int val;
		TreeNode left;
	    TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	public TreeNode insertNode(TreeNode root, TreeNode node) {
		if(root == null){
			root = node;
			return root;
		}
		if(node.val<root.val){
			root.left = insertNode(root.left, node);
		}else{
			root.right = insertNode(root.right, node);
		}
		return root;
	}
}
