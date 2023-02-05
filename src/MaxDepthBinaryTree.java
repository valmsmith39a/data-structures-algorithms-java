import java.util.*;
import java.util.LinkedList;
/**
 * Problem: Given a binary tree, find its maximum depth.
 * 
 * Background notes: 
 * 1. Depth of a node is the number of edges from node to the root.
 * 2. Height of a node is the number of edges from the node to the deepest leaf.
 * 3. Examples: 
 * Depth of a tree with a single node is 0.
 * Depth of a tree with a single root and two children is 1.
 * 
 * Solution Strategy: 
 * 1. Use depth-first search to recursively find the max depth of left and right subtrees. 
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class MaxDepthBinaryTree {
	Node root;

	public MaxDepthBinaryTree(int[] numbers) {
		root = buildBinaryTree(numbers);
	}

	public Node buildBinaryTree(int[] numbers) {
		Queue<Node> queue = new LinkedList<Node>();
		Node root = new Node(numbers[0]);
		queue.add(root);
		int i = 1;
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			if (i < numbers.length) {
				node.left = new Node(numbers[i++]);
				queue.add(node.left);
			}
			if (i < numbers.length) {
				node.right = new Node(numbers[i++]);
				queue.add(node.right);
			}
		}
		return root;
	}

	private class Node {
		int val;
		Node left;
		Node right;

		Node(int val) {
			this.val = val;
		}
	}

	public int getMaxDepth(Node root) {
		if (root == null) {
			return -1;
		}
		return Math.max(getMaxDepth(root.left), getMaxDepth(root.right)) + 1;
	}

	public static void main(String[] args) {	
		int[] numbers = new int[] { 0, 1, 2, 3, 4, 5, 6, 7 };
		MaxDepthBinaryTree bt = new MaxDepthBinaryTree(numbers);
		int maxDepth = bt.getMaxDepth(bt.root);
		System.out.println("Max depth of binary tree is: " + maxDepth);
	}
}


