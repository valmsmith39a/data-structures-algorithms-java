/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 * 
 * 1. Problem: #112 Path Sum
 * 2. Idea: DFS, checking remaining
 * 3. Complexity: O(n) time, O(h) space, where h is the height of the tree or
 * O(n) in the worst case of a skewed tree
 */
public class HasPathSum {
	public boolean hasPathSum(TreeNode root, int targetSum) {
		return dfs(root, targetSum);
	}

	private boolean dfs(TreeNode node, int remaining) {
		if (node == null)
			return false;
		remaining -= node.val;
		if (node.left == null && node.right == null)
			return remaining == 0;
		return dfs(node.left, remaining) || dfs(node.right, remaining);
	}

}
