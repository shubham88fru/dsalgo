package strvr.binarytree;

import java.util.*;

//@link - https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/description/
//@strvr - https://takeuforward.org/data-structure/zig-zag-traversal-of-binary-tree/
public class ZigZagTraversalOfBT {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        return traverseZigZag(root);
    }

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
