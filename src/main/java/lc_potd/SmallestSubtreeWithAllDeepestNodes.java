package lc_potd;

//@link - https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/
//@check - https://www.youtube.com/watch?v=ylpPcln87hI&t=2323s&ab_channel=codestorywithMIK
public class SmallestSubtreeWithAllDeepestNodes {
    /**
     * This problem is same at @see {@link lc_potd.LowestCommonAncestorOfDeepestLeaves}
     * Solved that one first and then copypasta the soln here.
     * The optimal approach there applies here as well.
     */
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return pass1(root);
    }

    private TreeNode pass1(TreeNode root) {
        int[] h = {0};

        height(root, 0, h);

        return lca(root, 0, h[0]);
    }

    private TreeNode lca(TreeNode root, int depth, int height) {
        if (root == null) return null;

        if (depth == height) return root;

        TreeNode left = lca(root.left, depth+1, height);
        TreeNode right = lca(root.right, depth+1, height);

        if (left != null && right != null) return root;
        if (left != null) return left;

        return right;
    }

    private void height(TreeNode root, int depth, int[] h) {
        if (root == null) return;

        h[0] = Math.max(h[0], depth);
        height(root.left, depth+1, h);
        height(root.right, depth+1, h);
    }
}
