
public class InvertBinaryTree {

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        invertTree(root.left);
        invertTree(root.right);

        return root;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1, null, null);
        TreeNode node2 = new TreeNode(2, null, null);
        TreeNode node3 = new TreeNode(3, null, null);

        node1.left = node2; 
        node1.right = node3; 
       
        // Expect: 1, 2, 3 
        System.out.println(node1.data);
        System.out.println(node1.left.data);
        System.out.println(node1.right.data);

        InvertBinaryTree ibt = new InvertBinaryTree();
        TreeNode root = ibt.invertTree(node1);

        // Expcect: 1, 3, 2 
        System.out.println(root.data);
        System.out.println(root.left.data);
        System.out.println(root.right.data);
    }
}

class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;

    public TreeNode(int data, TreeNode left, TreeNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}