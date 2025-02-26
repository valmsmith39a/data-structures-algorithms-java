/**
 * Time Complexity: O(n)
 * Space Complexity: Call stack O (log n)
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

	public static void main(String[] args) {
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		n1.left = n2;
		n1.right = n3;
		LowestCommonAncestor lca = new LowestCommonAncestor();
		TreeNode lowestCommonAncestorNode = lca.lowestCommonAncestor(n1, n2, n3);
		System.out.println("Lowest Common Ancestor node is: " + lowestCommonAncestorNode.val);
	}
}
