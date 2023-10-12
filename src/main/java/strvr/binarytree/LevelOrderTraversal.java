package strvr.binarytree;

import java.util.ArrayDeque;
import java.util.*;
import java.util.Deque;

//@link - https://leetcode.com/problems/binary-tree-level-order-traversal/
//@strvr - https://takeuforward.org/data-structure/level-order-traversal-of-a-binary-tree/
public class LevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> bfsTraversal = new ArrayList<>();

        bfs(root, bfsTraversal);

        return bfsTraversal;
    }

    private void bfs(TreeNode root, List<List<Integer>> bfsTraversal) {
        if (root == null) return;

        //Initialize the queue. We use a queue for BF Traversal.
        Deque<TreeNode> queue = new ArrayDeque<>();

        queue.addLast(root);

        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();

            //At any level, queue will have only the nodes at
            //that level.
            int remainingNodeToProcess = queue.size();

            //Process all nodes at current level
            while (remainingNodeToProcess != 0) {
                //keep removing the nodes untill
                //all nodes of that level are processed.
                TreeNode currNode = queue.removeFirst();
                level.add(currNode.val);
                remainingNodeToProcess -= 1;

                //Add left child of curr node to queue.
                if (currNode.left != null) {
                    queue.addLast(currNode.left);
                }

                //Add right child of curr node to queue.
                if (currNode.right != null) {
                    queue.addLast(currNode.right);
                }
            }
            bfsTraversal.add(level);
        }
    }
}