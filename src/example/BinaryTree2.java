
public class BinaryTree2 {

    public static void main(String[] args) {

        BinaryTree bt = new BinaryTree();

        bt.insert(8);
        bt.insert(7);
        bt.insert(12);
        bt.insert(15);
        bt.insert(2);
        bt.insert(5);

        //bt.inorder();
        bt.preorder();

    }
}

class Node {
    Node left, right;
    int data;

    public Node(int data) {
        this.data = data;
        left = right = null;
    }
}

class BinaryTree {
    private Node root;

    public void insert(int data) {
        root = insertRec(root, data);
    }

    private Node insertRec(Node root, int data) {
        if(root == null)
            root = new Node(data);
        else if ( data < root.data )
            root.left = insertRec(root.left, data);

        else if ( data > root.data )
            root.right = insertRec(root.right, data);

        return root;
    }

    public void inorder() {
        recInorder(root);
    }

    public void recInorder(Node root) {
        if(root != null) {
            recInorder(root.left);
            System.out.print(root.data + " ");
            recInorder(root.right);
        }
    }

    public void preorder() {
        recPreorder(root);
    }

    public void recPreorder(Node root) {
        if(root != null) {
            System.out.print(root.data + " ");
            recInorder(root.left);
            System.out.print(root.data + " ");

        }
    }

}