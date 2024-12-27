package lc_potd;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

//@link - https://leetcode.com/problems/find-largest-value-in-each-tree-row/
public class FindLargestValueInEachTreeRow {
    public List<Integer> largestValues(TreeNode root) {
        return bfs(root);
    }

    private List<Integer> bfs(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        Deque<TreeNode> q = new ArrayDeque<>();

        q.addLast(root);

        while (!q.isEmpty()) {
            int sz = q.size();
            int max = Integer.MIN_VALUE;
            while (sz > 0) {
                TreeNode node = q.removeFirst();
                if (node.val > max) {
                    max = node.val;
                }

                if (node.left != null) q.addLast(node.left);
                if (node.right != null) q.addLast(node.right);

                sz -= 1;
            }
            ans.add(max);
        }

        return ans;
    }
}
