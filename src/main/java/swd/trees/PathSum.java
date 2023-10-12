package swd.trees;


//@link - https://leetcode.com/problems/path-sum/description/
public class PathSum {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        return dfsForSum(root, targetSum);
    }

    //Pre-order traverse for target sum.
    private boolean dfsForSum(TreeNode root, int targetSum) {
        if (root == null) return false;

        //if leaf node check current targetsum equals
        //node value.
        if (root.left == null && root.right == null) {
            if (targetSum == root.val) return true;
        }

        //recursively traverse pre-order for target sum.
        targetSum -= root.val;
        boolean leftAns = dfsForSum(root.left, targetSum);
        boolean rightAns = dfsForSum(root.right, targetSum);

        return (leftAns || rightAns);
    }
}
