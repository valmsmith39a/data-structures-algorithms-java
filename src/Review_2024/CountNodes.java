package Review_2024;

public class CountNodes {

	public int countNodes(Node root) {
		return root != null ? 1 + countNodes(root.left) + countNodes(root.right) : 0;
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
