package basics.binary_tree;

public class PostOrderTraversal {

    //T:O(N), S: O(Height)
    void postorder(Node root) {
        if (root!=null) {
            //left-right-root
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.key+" ");
        }
    }

    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(20);
        root.right = new Node(30);
        root.right.left = new Node(40);
        root.right.right = new Node(50);

        PostOrderTraversal postOrderTraversal =
                new PostOrderTraversal();
        postOrderTraversal.postorder(root);
        System.out.println();
        System.out.println("-------------");
        postOrderTraversal.postorder(root);
    }
}
