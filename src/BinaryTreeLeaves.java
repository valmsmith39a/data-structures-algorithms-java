import java.util.*;

class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;

    TreeNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    TreeNode(int data, TreeNode left, TreeNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}

class BinaryTree {
    TreeNode root;

    BinaryTree() {
        this.root = null;
    }

    public void insert(int data) {
        this.root = insert(this.root, data);
    }

    public TreeNode insert(TreeNode node, int data) {
        if (node == null) {
            return new TreeNode(data);
        }
        node.left = insert(node.left, data);
        node.right = insert(node.right, data);
        return node;
    }
}

public class BinaryTreeLeaves {
    /**
     * Find Leaves of Binary tree 
     *
     * Problem Statement: 
     *  Given root of binary tree, collect and remove all leave nodes 
     *  until tree is empty.
     *
     * Leetcode url: https://leetcode.com/problems/find-leaves-of-binary-tree/
     * 
     * Example: 
     * Input: [1, 2, 3, 4, 5]
     * Output: [[4, 5, 3], [2], [1]]
     * 
     * Key insight: 
     *  Group all the nodes that are at the same tree height 
     *      height: number of edges from node to deepest leaf node 
     *          For null node, height is -1
     *          For single node, height is 0
     *          For 1 node, with 1 left child, height is 1 
     */
    private List<List<Integer>> solution;
    
    private int getHeight(TreeNode root) {
        // if root is null, height is -1
        // for a single node, height is 0 (-1 + 1) 
        if (root == null) {
            return -1;
        }
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);
        int currHeight = Math.max(leftHeight, rightHeight) + 1;
        if (this.solution.size() == currHeight) {
            this.solution.add(new ArrayList<>());
        }
        this.solution.get(currHeight).add(root.data);
        return currHeight;
    }

    public List<List<Integer>> findLeaves(TreeNode root) {
        this.solution = new ArrayList<>();
        getHeight(root);
        return this.solution;
    }

    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        bt.root = new TreeNode(1);
        bt.root.left = new TreeNode(2);
        bt.root.right = new TreeNode(3);
        bt.root.left.left = new TreeNode(4);
        bt.root.left.right = new TreeNode(5);

        // Solution: [[4, 5, 3], [2], [1]]
        BinaryTreeLeaves btl = new BinaryTreeLeaves();
        List<List<Integer>> leaves = btl.findLeaves(bt.root);
        for (List<Integer> setOfLeaves : leaves) {
            for (int leaf : setOfLeaves) {
                System.out.print(leaf);
            }
            System.out.println();
        }
    }
}
