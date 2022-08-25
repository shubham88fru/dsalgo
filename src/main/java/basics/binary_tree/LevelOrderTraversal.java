package basics.binary_tree;

import java.util.ArrayDeque;
import java.util.Queue;

//aka breadth first search of bt.
public class LevelOrderTraversal {

    //Recursion implementation of level order
    //traversal is not a good idea.
    //One way to indirectly use recursive sol
    //for level order traversal is two first find height
    //of bt and then use the node at distance k to print
    //each node from 0 to height. but this will be a
    //O(H*N) sol.

    //level order traversal can be solved in O(N)
    //using queue data structure.

    //T:Theta(N), S:O(width) --> width of binary tree.
    void levelOrderTraversal(Node root) {
        if (root == null) return;
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            System.out.print(curr.key + " ");
            if (curr.left != null)
                queue.add(curr.left);
            if (curr.right != null)
                queue.add(curr.right);
        }
    }

    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(20);
        root.right = new Node(30);
        root.right.right = new Node(6);
        root.left.left = new Node(8);
        root.left.right = new Node(7);
        root.left.right.left = new Node(9);
        root.left.right.right = new Node(15);

        LevelOrderTraversal levelOrderTraversal
                = new LevelOrderTraversal();
        levelOrderTraversal.levelOrderTraversal(root);
    }
}
