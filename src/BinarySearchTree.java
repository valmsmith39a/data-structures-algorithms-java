/**
 * Binary Search Tree
 * 	1. At most 2 child nodes
 * 	2. Left child < parent
 * 	3. Right child > parent 
 * 	4. No duplicate values 
 * 
 * @author George Wee
 * @version 1.0
 * 
 */
public class BinarySearchTree {

	public static class Node {
		Node left, right;
		int data;
		
		public Node(int data) {
			this.data = data;
		}
		
		public void insert(int value) {
			if (value < data) {
				if (left == null) {
					left = new Node(value);
				} else {
					left.insert(value);
				}
			} else {
				if (right == null) {
					right = new Node(value);
				} else {
					right.insert(value);
				}
			}
		}
		
		public void printInOrder() {
			if (left != null) {
				left.printInOrder();
			}
			System.out.println(data);
			if (right != null) {
				right.printInOrder();
			}
		}
		
		public void printPreOrder() {
			System.out.println(data);
			if (left != null) {
				left.printPreOrder();
			}
			if (right != null) {
				right.printPreOrder();
			}
		}
		
		public void printPostOrder() {
			if (left != null) {
				left.printPostOrder();
			}
			if (right != null) {
				right.printPostOrder();
			}
			System.out.println(data);
		}
		
		public void invertBinaryTree() {
			// swap left and right nodes
			Node leftNode = left;
			left = right;
			right = leftNode;
			
			if (left != null) {
				left.invertBinaryTree();
			}
			if (right != null) {
				right.invertBinaryTree();
			}
		}
	}

	public static void main(String args[]) {
		int[] values = new int[]{ 5, 3, 7, 6, 2, 9, 1, 4, 8, 10 };
		int index = 0;
		Node root = new Node(values[0]);
		
		for (int value : values) {
			if (index == 0) {
				index++;
				continue;
			} else {
				root.insert(value);
			}
			index++;
		}
		System.out.println("In Order Traveral: ");
		root.printInOrder();
		System.out.println("Pre-order Traversal: "); 
		root.printPreOrder();
		System.out.println("Post-order Traversal: ");
		root.printPostOrder();
		System.out.println("Invert binary tree: ");
		System.out.println("Original tree: ");
		root.printInOrder();
		root.invertBinaryTree();
		System.out.println("Inverted binary tree: ");
		root.printInOrder();
	}
}
