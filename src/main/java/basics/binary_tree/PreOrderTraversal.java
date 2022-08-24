package basics.binary_tree;

//Given root of a binary tree, print
//the inorder traversal of the tree.
public class PreOrderTraversal {

    //T: O(N) -> cause constant amt of work for every node.
    //S: O(height) -> cause there will atmost this num of recursive calls on stack
    void preorder(Node root) {
        if (root != null) {
            //root - left - right
            System.out.print(root.key+" ");
            preorder(root.left);
            preorder(root.right);
        }
    }

    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(20);
        root.right = new Node(30);
        root.right.left = new Node(40);
        root.right.right = new Node(50);

        PreOrderTraversal preOrderTraversal =
                new PreOrderTraversal();
        preOrderTraversal.preorder(root);
        System.out.println();
        System.out.println("-------------");
        preOrderTraversal.preorder(root);
    }
}
