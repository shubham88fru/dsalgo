package basics.binary_tree;

import java.util.ArrayDeque;

//Given root of a binary tree, print
//the inorder traversal of the tree.
public class PreOrderTraversal {

    //T: O(N) -> cause constant amt of work for every node.
    //S: O(height) -> cause there will atmost this num of recursive calls on stack
    void preorder(Node root) {
        if (root != null) {
            //root - left - right
            System.out.print(root.key + " ");
            preorder(root.left);
            preorder(root.right);
        }
    }

    //T:O(N), but S: O(H) therefore  spacewise
    //better than below soln. It optimises space because
    //it stores only right node in stack. while the below
    //soln pushes both left and right to stack.
    void preorderIterativeMy(Node root) {
        ArrayDeque<Node> stack = new ArrayDeque<>();
        Node curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                //print
                System.out.print(curr.key + " ");

                //keep pushing right node to stack.
                if (curr.right !=null)
                    stack.push(curr.right);

                //keep going left
                curr = curr.left;
            }

            if (!stack.isEmpty())
                curr = stack.pop();
        }
    }

    //T:O(N), S:O(N) --> Note: Not O(H) space.
    //Therefore, space wise this is not better than
    //above soln.
    void preorderIterativeSir(Node root) {
        if (root==null) return;
        ArrayDeque<Node> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node curr = stack.pop();
            System.out.print(curr.key+" ");
            if (curr.right != null) stack.push(curr.right);
            if (curr.left != null) stack.push(curr.left);
        }
    }

    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(20);
        root.right = new Node(30);
        root.right.left = new Node(40);
        root.right.right = new Node(50);

        Node root2 = new Node(10);
        root2.left = new Node(20);
        root2.left.left = new Node(40);
        root2.left.right = new Node(50);
        root2.right = new Node(30);
        root2.right.left = new Node(60);

        PreOrderTraversal preOrderTraversal =
                new PreOrderTraversal();
        preOrderTraversal.preorder(root);
        System.out.println();
        System.out.println("-------------");
        preOrderTraversal.preorderIterativeMy(root);
        System.out.println();
        System.out.println("-------------");
        preOrderTraversal.preorderIterativeSir(root);
        System.out.println();
        System.out.println("==============");
        preOrderTraversal.preorder(root2);
        System.out.println();
        System.out.println("-------------");
        preOrderTraversal.preorderIterativeMy(root2);
        System.out.println();
        System.out.println("-------------");
        preOrderTraversal.preorderIterativeSir(root2);
    }
}
