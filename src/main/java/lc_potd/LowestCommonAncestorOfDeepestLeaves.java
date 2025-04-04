package lc_potd;

//@link - https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/
//@check - https://www.youtube.com/watch?v=ylpPcln87hI&t=2323s&ab_channel=codestorywithMIK
public class LowestCommonAncestorOfDeepestLeaves {

    public TreeNode lcaDeepestLeaves(TreeNode root) {
        return pass1(root);
    }

    /**
     * Following is my approach.
     * 2 pass soln, miks also showed this approach.
     * However, this ain't the most optimal soln.

     * Mik showed a one pass soln too, which is actually
     * not very difficult to comprehend.
     * @check for the one pass approach if this is FAQ for some company.
     */
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
