package lc_potd;

//@link - https://leetcode.com/problems/maximum-product-of-splitted-binary-tree/description/
public class MaximumProductOfSplittedBinaryTree {
    public int maxProduct(TreeNode root) {
        // return revise(root);
        return cleaner(root);
    }

    private int cleaner(TreeNode root) {
        long ts = dfs(root);
        long[] ans = {0};
        dfs2(root, ans, ts);
        return (int)(ans[0]%1000000007);
    }

    private long dfs2(TreeNode root, long[] ans, long ts) {
        if (root == null) return 0l;

        long left = dfs2(root.left, ans, ts);
        long right = dfs2(root.right, ans, ts);

        long subtreeSum = (root.val + left + right);
        ans[0] = Math.max(ans[0], ((ts-subtreeSum)*subtreeSum));
        return subtreeSum;
    }

    private int revise(TreeNode root) {
        long ts = dfs(root);
        long[] ans = {0};
        maxSplit(root, ans, ts);
        return (int)(ans[0]%1000000007);
    }

    private long[] maxSplit(TreeNode root, long[] ans, long ts) {
        if (root == null) return new long[]{0, 0};

        long[] left = maxSplit(root.left, ans, ts);
        long[] right = maxSplit(root.right, ans, ts);

        ans[0] = Math.max(ans[0], Math.max(left[1], right[1]));
        long subtreeSum = (root.val + left[0] + right[0]);
        long pdt = ((ts-subtreeSum)*subtreeSum);
        return new long[]{subtreeSum, pdt};
    }

    private long dfs(TreeNode root) {
        if (root == null) return 0;

        long left = dfs(root.left);
        long right = dfs(root.right);

        return (root.val + left + right);
    }
}
