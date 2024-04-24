package ptrn.trees.dfs;

//@link - https://leetcode.com/problems/path-sum/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6589436294070272
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

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
