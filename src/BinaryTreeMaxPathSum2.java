
import java.util.*;
/**
 * Binary Tree Maximum Path Sum 
 * 
 * Problem Description:
 * Given a binary tree, find the maximum path sum.
 * The path may start and end at any node in the tree.  
 * 
 * Key insights:
 * 1. Can break the tree into subtrees of 1 node, left child, right child. 
 * 2. The max path sum of a subtree is the sum of left child, node, right child. 
 * 3. The max value that can be returned by a node (to be used by parent node), however, is node + max(left child, right child),
 *    because the path cannot branch out (can only go left or right). 
 * 4. When calculating the max path sum at the root node, we sum up the left child, node, right child and compare
 *    it with the max path sum up to that point.
 * 
 * For points 3 and 4, another way to think about it is: 
 * 1. From the perspective of the root node, once you get to a child node, you can only pick one path (left or right), 
 * so the max value at that node is the node value + max(left child, right child).
 * 
 * 2. From the perspective of each subtree, the max path is the sum of the left child, node, right child.
 * 
 * Time Complexity: O(n), because we visit each node once. 
 * Space Complexity: O(h), where h is the height of the tree because maximum recursive calls stored
 * 				     in the call stack is the height of the tree. We can also say that the space complexity 
 *                   is O(log(n)) for the average case, if the tree is balanced, or O(n) in the worst case if the tree is skewed, 
 * 				     i.e. all nodes have only one child.
 *  or O(log(n)) if the tree is balanced. 
 */
public class BinaryTreeMaxPathSum2 {
	private int maxSum = Integer.MIN_VALUE;		

	public int maxPathSum(TreeNode root) {
		dfs(root);
		return maxSum;
	}

	public int dfs(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int leftSum = Math.max(0, dfs(root.left));
		int rightSum = Math.max(0, dfs(root.right));
		int pathSum = leftSum + rightSum + root.val;
		maxSum = Math.max(maxSum, pathSum);
		return Math.max(leftSum, rightSum) + root.val;
	}

	private static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}

	public static void main(String[] args) {
		BinaryTreeMaxPathSum2 solution = new BinaryTreeMaxPathSum2();
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		// Expected: 6
		System.out.println(solution.maxPathSum(root));
	}
}
