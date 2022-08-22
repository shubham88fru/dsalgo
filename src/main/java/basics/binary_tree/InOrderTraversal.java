package basics.binary_tree;

//Given root of a binary tree, print
//the inorder traversal of the tree.
public class InOrderTraversal {

    void inorderMy(Node root) {
        if (root==null) return;
        //leaf node.
        if (root.left==null && root.right==null) {
            System.out.print(root.key+" ");
            return;
        }

        //Inorder is - Left-Root-Right
        inorderMy(root.left);
        System.out.print(root.key+" ");
        inorderMy(root.right);
    }

    //T:O(N), S:O(H) ; where `N` is no. of nodes, `H` is height of binary tree.
    void inorderSir(Node root) {
        if (root!=null) {
            inorderSir(root.left);
            System.out.print(root.key+" ");
            inorderSir(root.right);
        }
    }

    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(20);
        root.right = new Node(30);
        root.right.left = new Node(40);
        root.right.right = new Node(50);

        InOrderTraversal inOrderTraversal =
                new InOrderTraversal();
        inOrderTraversal.inorderMy(root);
        System.out.println("-------------");
        inOrderTraversal.inorderSir(root);
    }
}
