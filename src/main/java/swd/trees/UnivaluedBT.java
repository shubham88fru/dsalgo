package swd.trees;

import java.util.ArrayDeque;
import java.util.Deque;

//@link - https://leetcode.com/problems/univalued-binary-tree/description/
public class UnivaluedBT {
    public boolean isUnivalTree(TreeNode root) {
        //return isUnivaluedDFS(root, root.val);
        return isUnivaluedBFS(root);
    }

    //1) DFS algorithm for this problem.
    private boolean isUnivaluedDFS(TreeNode root, int val) {
        //If reached till leaf and we couldn't find a node which is unmatching, return true
        if (root == null) return true;

        //traverse preorder and check if
        //curr nodes val is same as root's val and left subtree and right subtree are univalued.
        return (val == root.val) && isUnivaluedDFS(root.left, val) && isUnivaluedDFS(root.right, val);
    }

    //2) BFS algorithm for this problem.
    private boolean isUnivaluedBFS(TreeNode root) {
        if (root == null) return true;

        //Initialize queue for bfs.
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.addLast(root);

        //value to be matched with every node.
        int target = root.val;

        //run bfs.
        while (!queue.isEmpty()) {

            TreeNode currNode = queue.removeFirst();

            //if any node's value is diff, break immediately
            //and return false.
            if (currNode.val != target) return false;

            if (currNode.left != null) queue.addLast(currNode.left);
            if (currNode.right != null) queue.addLast(currNode.right);

        }

        //If here, means all nodes value are same.
        //Hence, tree must be univalued.
        return true;
    }
}
