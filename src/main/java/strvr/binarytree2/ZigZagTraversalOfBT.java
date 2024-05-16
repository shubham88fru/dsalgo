package strvr.binarytree2;

import java.util.*;

//@link - https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/description/
//@strvr - https://takeuforward.org/data-structure/zig-zag-traversal-of-binary-tree/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6488203193483264
public class ZigZagTraversalOfBT {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        //return traverseZigZag(root);
        return traverseZigZagOptimal(root);
    }

    //1) Optimal - using a deque (not just as a simple queue).
    //Alternate between the FIFO and LIFO behaviors at each level.
    private List<List<Integer>> traverseZigZagOptimal(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;

        /**
         will use the double ended feature of the deque.
         */
        Deque<TreeNode> q = new ArrayDeque<>();
        q.addLast(root);
        boolean reverseLevel = false;

        while (!q.isEmpty()) {
            int sz = q.size();
            List<Integer> level = new ArrayList<>();
            while (sz > 0) {
                if (!reverseLevel) {
                    //If reverse level isn't true,
                    //we'll follow the normal BFS approach.
                    //i.e. (Remove node - removeFirst()) -->
                    //(add left - addLast()) --> (add right - addLast()).
                    TreeNode node = q.removeFirst();
                    level.add(node.val);
                    if (node.left != null) q.addLast(node.left);
                    if (node.right != null) q.addLast(node.right);

                } else {
                    //Otherwise, if reverse level is true,
                    //we'll sort of reverse the normal BFS approach.
                    //i.e. (Remove node - removeLast()) -->
                    //(add right - addFirst()) --> (add left - addFirst()).
                    TreeNode node = q.removeLast();
                    level.add(node.val);
                    if (node.right != null) q.addFirst(node.right);
                    if (node.left != null) q.addFirst(node.left);
                }

                sz -= 1;
            }
            ans.add(level);
            reverseLevel = !reverseLevel;
        }

        return ans;
    }

    //2) Suboptimal soln.
    private List<List<Integer>> traverseZigZag(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;

        //standard bfs.
        Deque<TreeNode> q = new ArrayDeque<>();
        q.addLast(root);
        boolean reverse = false;

        while (!q.isEmpty()) {
            int sz = q.size();
            List<Integer> level = new ArrayList<>();
            while (sz > 0) {
                TreeNode curr = q.removeFirst();
                if (curr.left != null) q.addLast(curr.left);
                if (curr.right != null) q.addLast(curr.right);

                level.add(curr.val);
                sz -= 1;
            }

            //If current level has to printed in reverse order,
            //reverse the collection.
            if (reverse) Collections.reverse(level);

            //Keep switching the reverse flag in each level.
            reverse = !reverse;
            ans.add(level);
        }

        return ans;
    }
}
