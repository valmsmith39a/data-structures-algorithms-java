/**
 * Binary Search Tree
 * 1. At most 2 child nodes
 * 2. Left child < parent
 * 3. Right child > parent
 * 4. No duplicate values
 * 
 * @author George Wee
 * @version 1.0
 * 
 */
public class BinarySearchTree {
	Node root;

	public class Node {
		int data;
		Node left, right;

		public Node(int data) {
			this.data = data;
			this.left = null;
			this.right = null;
		}
	}

	public void insert(int data) {
		this.root = insert(this.root, data);
	}

	public Node insert(Node node, int data) {
		if (node == null) {
			return new Node(data);
		}
		if (data < node.data) {
			node.left = insert(node.left, data);
		} else if (data > node.data) {
			node.right = insert(node.right, data);
		}
		return node;
	}

	public void printInOrder(Node node) {
		if (node != null) {
			printInOrder(node.left);
			System.out.println(node.data);
			printInOrder(node.right);
		}
	}

	public void printPreOrder(Node node) {
		if (node != null) {
			System.out.println(node.data);
			printPreOrder(node.left);
			printPreOrder(node.right);
		}
	}

	public void printPostOrder(Node node) {
		if (node != null) {
			printPostOrder(node.left);
			printPostOrder(node.right);
			System.out.println(node.data);
		}
	}

	// For every node, swap left and right child nodes
	// https://www.educative.io/edpresso/how-to-invert-a-binary-tree
	public void invertBinaryTree(Node node) {
		if (node != null) {
			// swap left and right nodes
			Node leftNode = node.left;
			node.left = node.right;
			node.right = leftNode;

			invertBinaryTree(node.left);
			invertBinaryTree(node.right);
		}
	}

	public static void main(String args[]) {
		int[] values = new int[] { 5, 3, 7, 6, 2, 9, 1, 4, 8, 10 };
		BinarySearchTree bst = new BinarySearchTree();

		for (int value : values) {
			bst.insert(value);
		}
		System.out.println("values 1: ");
		// 1. 2. 3, 4, 5, 6, 7, 8, 9, 10
		System.out.println("In Order Traveral: ");
		bst.printInOrder(bst.root);
		// 5, 3, 2, 1, 4, 7, 6, 9, 8, 10
		System.out.println("Pre-order Traversal: ");
		bst.printPreOrder(bst.root);
		// 1, 2, 4, 3, 6, 8, 10, 9, 7, 5
		System.out.println("Post-order Traversal: ");
		bst.printPostOrder(bst.root);
		System.out.println("Invert binary tree: ");
		System.out.println("Original tree: ");
		bst.printInOrder(bst.root);
		bst.invertBinaryTree(bst.root);
		System.out.println("Inverted binary tree: ");
		bst.printInOrder(bst.root);

		// https://www.cs.rochester.edu/u/gildea/csc282/slides/C12-bst.pdf
		int[] values2 = new int[] { 7, 4, 12, 2, 6, 3, 5, 9, 8, 11, 19, 15, 20 };
		BinarySearchTree bst2 = new BinarySearchTree();
		for (int value : values2) {
			bst2.insert(value);
		}
		System.out.println("------------- values 2: ---------------");
		System.out.println("In Order Traveral: ");
		bst2.printInOrder(bst2.root);
		System.out.println("Pre-order Traversal: ");
		bst2.printPreOrder(bst2.root);
		System.out.println("Post-order Traversal: ");
		bst2.printPostOrder(bst2.root);
		System.out.println("Invert binary tree: ");
		System.out.println("Original tree: ");
		bst2.printInOrder(bst2.root);
		bst2.invertBinaryTree(bst2.root);
		System.out.println("Inverted binary tree: ");
		bst2.printInOrder(bst2.root);
	}
}
