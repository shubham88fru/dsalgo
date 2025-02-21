package lc_potd;

// @link - https://leetcode.com/problems/count-good-nodes-in-binary-tree/
public class CountGoodNodesInBinaryTree {
    public int goodNodes(TreeNode root) {
        return pass1(root);
    }

    private int pass1(TreeNode root) {
        int[] count = {0};

        dfs(root, Integer.MIN_VALUE, count);
        return count[0];
    }

    private void dfs(TreeNode root, int max, int[] count) {
        if (root == null) return;

        if (root.val >= max) {
            count[0] += 1;
        }

        dfs(root.left, Math.max(max, root.val), count);
        dfs(root.right, Math.max(max, root.val), count);
    }
}
