package swd.trees;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

//@link - https://leetcode.com/problems/n-ary-tree-level-order-traversal/description/
public class BreadthFirstTraversalOfNAT {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> bfsTraversal = new ArrayList<>();

        bfs(root, bfsTraversal);

        return bfsTraversal;
    }

    private void bfs(Node root, List<List<Integer>> bfsTraversal) {
        if (root == null) return;

        //Initialize the queue. We use a queue for BF Traversal.
        Deque<Node> queue = new ArrayDeque<>();

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
                Node currNode = queue.removeFirst();
                level.add(currNode.val);
                remainingNodeToProcess -= 1;

                //Add all children of curr node to
                //queue.
                for (Node child: currNode.children) {
                    queue.addLast(child);
                }
            }
            bfsTraversal.add(level);
        }
    }
}
