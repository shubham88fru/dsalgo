package ptrn.trees.dfs;

//@link - https://leetcode.com/problems/minimum-depth-of-binary-tree/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6147090431606784
public class MinDepthOfBT {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        int[] min = new int[]{Integer.MAX_VALUE};
        minDep(root, min, 1);
        return min[0];
    }

    private void minDep(TreeNode root, int[] min, int height) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            min[0] = Math.min(min[0], height);
        }

        minDep(root.left, min, height + 1);
        minDep(root.right, min, height + 1);
    }
}
