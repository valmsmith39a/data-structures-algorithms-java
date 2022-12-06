import java.util.*;
import java.util.LinkedList;

public class BinaryTree {
    public Node root;

    public BinaryTree() {
        // NOP
    }

    /**
     * Depth of node: number of edges from root node to
     * the target node
     */
    // public int getDepthOfNode(Node root, Node target) {
    // if (root == null) {
    // return -1;
    // }
    // int depth = -1;
    // if (root == target
    // || (depth = getDepthOfNode(root.left, target)) >= 0
    // || (depth = getDepthOfNode(root.right, target)) >= 0) {
    // return depth + 1;
    // }
    // return depth;
    // }

    public int getDepthOfNode(Node root, Node target) {
        if (root == null) {
            return -1;
        }
        if (root == target) {
            return 0;
        }
        int leftDepth = getDepthOfNode(root.left, target);
        int rightDepth = getDepthOfNode(root.right, target);
        if (leftDepth >= 0) {
            return leftDepth + 1;
        }
        if (rightDepth >= 0) {
            return rightDepth + 1;
        }
        return -1;
    }

    /**
     * Height of node: number of edges from node to the
     * farthest leaf node
     */
    public int getHeightOfNode(Node node) {
        if (node == null) {
            return -1;
        }
        return Math.max(getHeightOfNode(node.left), getHeightOfNode(node.right)) + 1;
    }

    public Node insertLevelOrder(int[] arr, int i) {
        Node root = null;
        if (i < arr.length) {
            root = new Node(arr[i]);
            root.left = insertLevelOrder(arr, 2 * i + 1);
            root.right = insertLevelOrder(arr, 2 * i + 2);
        }
        return root;
    }

    public void printLevelOrder() {
        LinkedList<Node> nextToVisit = new LinkedList<Node>();
        nextToVisit.add(root);
        while (!nextToVisit.isEmpty()) {
            Node node = nextToVisit.remove();
            System.out.println(node.data);
            if (node.left != null) {
                nextToVisit.add(node.left);
            }
            if (node.right != null) {
                nextToVisit.add(node.right);
            }
        }
    }

    public Node getNode(Node root, int target) {
        if (root == null) {
            return null;
        }
        if (root.data == target) {
            return root;
        }
        List<Node> children = new ArrayList<Node>(Arrays.asList(root.left, root.right));
        for (Node child : children) {
            Node node = getNode(child, target);
            if (node != null) {
                return node;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        int[] arr = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        bt.root = bt.insertLevelOrder(arr, 0);
        // Height for node 1 is 3
        Node node1 = bt.getNode(bt.root, 1);
        System.out.println("node is: " + node1.data);
        System.out.println("node height is: " + bt.getHeightOfNode(node1));
        // Height for node 2 is 2
        Node node2 = bt.getNode(bt.root, 2);
        System.out.println("node is: " + node2.data);
        System.out.println("node height is: " + bt.getHeightOfNode(node2));
        // Height for node 3 is 1
        Node node3 = bt.getNode(bt.root, 3);
        System.out.println("node is: " + node3.data);
        System.out.println("node height is: " + bt.getHeightOfNode(node3));
        // Height for node 8 is 0
        Node node8 = bt.getNode(bt.root, 8);
        System.out.println("node is: " + node8.data);
        System.out.println("node height is: " + bt.getHeightOfNode(node8));
        // Height for node 9 is 0
        Node node9 = bt.getNode(bt.root, 9);
        System.out.println("node is: " + node9.data);
        System.out.println("node height is: " + bt.getHeightOfNode(node9));

        // Depth for node 1 is 0
        System.out.println("depth for node 1 is: " + bt.getDepthOfNode(bt.root, node1));
        // Depth for node 2 is 1
        System.out.println("depth for node 2 is: " + bt.getDepthOfNode(bt.root, node2));
        // Depth for node 5 is 2
        Node node5 = bt.getNode(bt.root, 5);
        System.out.println("depth for node 5 is: " + bt.getDepthOfNode(bt.root, node5));
        // Depth for node 8 is 3
        System.out.println("depth for node 8 is: " + bt.getDepthOfNode(bt.root, node8));
        // Depth of node 9 is 3
        System.out.println("depth for node 9 is: " + bt.getDepthOfNode(bt.root, node9));
    }
}

class Node {
    int data;
    Node left, right;

    public Node(int data) {
        this.data = data;
    }
}
