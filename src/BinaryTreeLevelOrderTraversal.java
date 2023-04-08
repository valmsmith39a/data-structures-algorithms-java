import java.util.*;
import java.util.LinkedList;
/**
 *  Problem: Binary Tree Level Order Traversal
 *  Description: Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
 *  Key Insights: 
 * 	    1. Breadth First Search
 * 	    2. Use a queue to store the nodes in each level
 *      3. Iterate through each level 
 *  Time Complexity: O(n)
 *  Space Complexity: O(n)
 */
public class BinaryTreeLevelOrderTraversal {

	private static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}

	public List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		if (root == null) {
			return res;
		}
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		
		while (!queue.isEmpty()) {

			int levelSize = queue.size();
			List<Integer> currentLevel = new ArrayList<>();

			for (int i = 0; i < levelSize; i++) {
				TreeNode node = queue.poll();
				currentLevel.add(node.val);
				if (node.left != null) {
					queue.offer(node.left);
				}
				if (node.right != null) {
					queue.offer(node.right);
				}
			}
			res.add(currentLevel);
		}
		return res;
	}

	public static void main(String[] args) {
		BinaryTreeLevelOrderTraversal solution = new BinaryTreeLevelOrderTraversal();
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(9);
		root.right = new TreeNode(20);
		root.right.left = new TreeNode(15);
		root.right.right = new TreeNode(7);
		List<List<Integer>> res = solution.levelOrder(root);
		// Expected: [[3], [9, 20], [15, 7]]
		System.out.println(res);
	}
}
