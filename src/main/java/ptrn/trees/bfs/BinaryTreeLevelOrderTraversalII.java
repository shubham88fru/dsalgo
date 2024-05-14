package ptrn.trees.bfs;

import java.util.*;

//@link - https://leetcode.com/problems/binary-tree-level-order-traversal-ii/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6147090431606784
public class BinaryTreeLevelOrderTraversalII {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> bfsTraversal = new ArrayList<>();

        bfs(root, bfsTraversal);

        Collections.reverse(bfsTraversal);

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

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

