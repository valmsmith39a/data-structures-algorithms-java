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
		root.printInOrder();
	}
}
