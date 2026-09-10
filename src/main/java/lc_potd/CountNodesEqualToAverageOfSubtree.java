package lc_potd;

//@link - https://leetcode.com/problems/count-nodes-equal-to-average-of-subtree/?
public class CountNodesEqualToAverageOfSubtree {
    public int averageOfSubtree(TreeNode root) {
        return pass1(root);
    }

    private int pass1(TreeNode root) {

        int[] ans = dfs(root);
        return ans[0];
    }

    private int[] dfs(TreeNode root) {
        if (root == null) return new int[] {0, 0, 0};

        int[] left = dfs(root.left);
        int[] right = dfs(root.right);

        int subtreeSum = root.val + left[1] + right[1];
        int subtreeNodes = 1 + left[2] + right[2];
        int avg = subtreeSum/subtreeNodes;
        if (avg == root.val) return new int[]{left[0]+right[0]+1, subtreeSum, subtreeNodes};

        return new int[]{left[0]+right[0], subtreeSum, subtreeNodes};

    }
}
