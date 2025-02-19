/**
 * Time Complexity: O(n)
 * Space Complexity: Call stack O (log n)
 */
public class LowestCommonAncestor {

	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null) {
			return null;
		}

		// Found either p or q
		if (root == p || root == q) {
			return root;
		}

		TreeNode left = lowestCommonAncestor(root.left, p, q);
		TreeNode right = lowestCommonAncestor(root.right, p, q);

		// if left subtree has p or q and right subtree has p or q then the root must be
		// the parent;
		if (left != null && right != null) {
			return root;
		}
		return left != null ? left : right;
	}
}
