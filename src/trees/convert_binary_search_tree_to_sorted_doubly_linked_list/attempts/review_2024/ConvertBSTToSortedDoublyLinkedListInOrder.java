package Review_2024;

/**
 * Convert Binary Search Tree to Doubly Linked List #426
 * 
 * Key Insights:
 * 1. In order traversal to provide ascending sorted order
 * 2. Be sure to link the first and last nodes to close the circular doubly
 * linked list
 * 
 * Time Complexity: O(n), bc traverse every node in the binary tree
 * Space Complexity: O(log n), bc size of recursive call stack is height of the
 * tree.
 */
public class ConvertBSTSortedDoublyLinkedList {
	Node first;
	Node last;

	public Node treeToDoublyList(Node root) {
		if (root == null)
			return null;

		helper(root);
		first.left = last;
		last.right = first;
		return first;
	}

	private void helper(Node node) {
		if (node != null) {
			// left child
			helper(node.left);

			// process node
			if (last != null) {
				last.right = node;
				node.left = last;
			} else {
				first = node;
			}
			last = node;

			// right child
			helper(node.right);
		}
		return;
	}

	class Node {
		public int val;
		public Node left;
		public Node right;

		public Node() {
		}

		public Node(int _val) {
			val = _val;
		}

		public Node(int _val, Node _left, Node _right) {
			val = _val;
			left = _left;
			right = _right;
		}
	}
}
