package basics.binary_tree;

//Height of binary tree is maximum no.
//of nodes from root to leaf.
public class HeightOfBinaryTree {

    //T: O(N), S:O(H)
    int height(Node root) {
        if (root == null)
            return 0;
        else {
            //height is max of height of left subtree
            //or right subtree.
            return Math.max(height(root.left),
                    height(root.right)) + 1;
        }

    }

    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(8);
        root.right = new Node(30);
        root.right.left = new Node(40);
        root.right.right = new Node(50);

        HeightOfBinaryTree height
                = new HeightOfBinaryTree();
        System.out.println(height.height(root));
    }
}
