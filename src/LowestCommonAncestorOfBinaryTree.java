/**
 * Problem: Lowest Common Ancestor of Binary Tree (#236)
 * 
 * Key Insights: 
 * 1. If a target node is the root node, that is the lowest common ancestor at that node. Cannot be further down the tree. 
 * 2. If target nodes p and q found in different subtrees, the lowest common ancestor is the current root node. 
 * 
 * Time Complexity: O(n)
 * Space Complexity: 
 * 1. O(h), h = height of the binary tree 
 * 2. Average case: O(log n), n = number of nodes in tree. Height of balanced binary tree is log n 
 * 3. Worst case: O(n), tree is skewed, where every node only has 1 child. 
 */
public class LowestCommonAncestorOfBinaryTree {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == root || q == root) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }
    
}

class TreeNode {
    int val; 
    TreeNode left;
    TreeNode right; 
    TreeNode(int x) { val = x; }
}
