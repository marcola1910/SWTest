import java.util.*;

class LCABinaryTree{
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Stack<TreeNode> stack = new Stack<>(); // for tree traversal
        Map<TreeNode, TreeNode> parent = new HashMap<>(); // map for storing parent pointers
        parent.put(root, null);
        stack.push(root);

        // iterate until both the nodes p and q are found
        while(!parent.containsKey(p) || !parent.containsKey(q)){
            TreeNode node = stack.pop();
            if(node.left != null){
                //keep saving the parent pointers
                parent.put(node.left,node);
                stack.push(node.left);
            }
            if(node.right != null){
                parent.put(node.right,node);
                stack.push(node.right);
            }
        }
        // Ancestor set for node p
        Set<TreeNode> ancestor = new HashSet<>();
        while(p != null){
            ancestor.add(p);
            p = parent.get(p);
        }
        //first ancestor of q which appears in p's ancestor is LCA
        while(!ancestor.contains(q)){
            q = parent.get(q);
        }
        return q;
    }
}

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

 class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Example: Constructing a binary tree manually
        // You can construct the tree as per your specific needs
        // Example tree:
        //         3
        //        / \
        //       5   1
        //      / \ / \
        //     6  2 0  8
        //       / \
        //      7   4
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        // Taking inputs for nodes p and q
        System.out.println("Enter the value of node p: ");
        int pVal = scanner.nextInt();
        System.out.println("Enter the value of node q: ");
        int qVal = scanner.nextInt();

        // Finding nodes p and q in the tree
        TreeNode p = findNode(root, pVal);
        TreeNode q = findNode(root, qVal);

        if (p == null || q == null) {
            System.out.println("One or both of the nodes not found in the tree.");
            return;
        }

        LCABinaryTree lcaFinder = new LCABinaryTree();
        TreeNode lca = lcaFinder.lowestCommonAncestor(root, p, q);

        if (lca != null) {
            System.out.println("The lowest common ancestor of nodes " + pVal + " and " + qVal + " is: " + lca.val);
        } else {
            System.out.println("Lowest common ancestor not found.");
        }
    }

    // Helper method to find a node with a specific value in the tree
    private static TreeNode findNode(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val == val) {
            return root;
        }
        TreeNode left = findNode(root.left, val);
        if (left != null) {
            return left;
        }
        return findNode(root.right, val);
    }
}
