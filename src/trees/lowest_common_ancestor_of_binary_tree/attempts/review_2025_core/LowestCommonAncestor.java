package Review_2025_2;

/**
 * Time Complexity: O(n)
 * Space Complexity: O(log n)
 */
public class LowestCommonAncestor {
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		public TreeNode(int val) {
			this.val = val;
			this.left = null;
			this.right = null;
		}
	}

	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null || root == p || root == q) {
			return root;
		}
		TreeNode left = lowestCommonAncestor(root.left, p, q);
		TreeNode right = lowestCommonAncestor(root.right, p, q);
		if (left != null && right != null) {
			return root;
		}
		return left != null ? left : right;
	}
}
