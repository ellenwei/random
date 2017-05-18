
public class DeleteBST {
	public class TreeNode {
		int val;
		TreeNode left;
	    TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	public TreeNode deleteNode(TreeNode root, int key) {
		if(root == null){
			return null;
		}else if(root.val>key){
			root.left = deleteNode(root.left, key);
		}else if(root.val<key){
			root.right = deleteNode(root.right, key);
		}else{
			if(root.left == null){
				return root.right;
			}else if(root.right == null){
				return root.left;
			}else{
				TreeNode min = findMin(root.right);
				root.val = min.val;
				root.right = deleteNode(root.right, root.val);
			}	
		}
		return root;
	}

	
	private TreeNode findMin(TreeNode root){
		while(root != null){
			root = root.left;
		}
		return root;
	}
}
