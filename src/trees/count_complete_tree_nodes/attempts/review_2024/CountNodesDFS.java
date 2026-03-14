package Review_2024;

/**
 * CountNodes
 * 
 * Simple solution
 * 1. Depth first search to count all the nodes
 * 2. O(n) time. O (log n) space for a balanced tree. O(n) space for skewed tree
 * in worst case
 * 
 * Optimized
 * 1. Since it's a complete binary tree (all levels are filled except possibly
 * the last one)
 * 2. Can use the formula 2^d - 1, where d = depth of the tree, to find the
 * number of nodes excluding last level.
 * 3. Then use binary search to find the number of nodes in the last level.
 * 4. Time complexity is: O((logn)^2)
 * 5. Space complexity is: O(log n)
 */
public class CountNodes {

	public int countNodes(Node root) {
		if (root == null) {
			return 0;
		}
		return countNodes(root.left) + countNodes(root.right) + 1;
	}

	private static class Node {
		int val;
		Node left;
		Node right;

		private Node() {
			// no-op
		}

		private Node(int val, Node left, Node right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}

	public static void main(String[] args) {
		Node node1 = new Node();
		Node node2 = new Node();
		Node node3 = new Node();
		node1.val = 1;
		node1.left = node2;
		node1.right = node3;
		CountNodes cn = new CountNodes();
		System.out.println(cn.countNodes(node1));
	}
}
