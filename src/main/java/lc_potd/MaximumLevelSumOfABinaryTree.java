package lc_potd;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.TreeMap;

//@link - https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/description/?
public class MaximumLevelSumOfABinaryTree {
    public int maxLevelSum(TreeNode root) {
        return bfs(root);
        // return approach2(root);
    }

    private int bfs(TreeNode root) {
        Deque<TreeNode> q = new ArrayDeque<>();

        int maxSum = Integer.MIN_VALUE;

        int maxLevel = -1;
        q.addLast(root);
        int level = 1;

        while (!q.isEmpty()) {
            int sz = q.size();
            int sum = 0;
            while (sz > 0) {
                TreeNode node = q.removeFirst();
                sum += node.val;

                if (node.left != null) q.addLast(node.left);
                if (node.right != null) q.addLast(node.right);

                sz -= 1;
            }
            if (sum > maxSum) {
                maxSum = sum;
                maxLevel = level;
            }
            level += 1;
        }

        return maxLevel;
    }

    private int approach2(TreeNode root) {
        TreeMap<Integer, Integer> tm = new TreeMap<>();

        dfs(root, 1, tm);

        int maxSum = Integer.MIN_VALUE;
        int maxLevel = -1;
        for (Map.Entry<Integer, Integer> entry: tm.entrySet()) {
            if (entry.getValue() > maxSum) {
                maxSum = entry.getValue();
                maxLevel = entry.getKey();
            }
        }

        return maxLevel;
    }

    private void dfs(TreeNode root, int l, TreeMap<Integer, Integer> tm) {
        if (root == null) return;

        dfs(root.left, l+1, tm);
        tm.put(l, tm.getOrDefault(l, 0)+root.val);
        dfs(root.right, l+1, tm);
    }
}
