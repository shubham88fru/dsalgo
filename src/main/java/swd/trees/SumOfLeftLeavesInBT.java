package swd.trees;

//@link - https://leetcode.com/problems/sum-of-left-leaves/description/
public class SumOfLeftLeavesInBT {
    /*** My Soln - Works but kinda nasty code. ***/
    public int sumOfLeftLeaves(TreeNode root) {
        return dfsForLeftLeaves(root, false, 0);
    }

    private int dfsForLeftLeaves(TreeNode root, boolean isLeftChild, int sum) {
        if (root == null) return sum;
        if (root.left == null && root.right == null && isLeftChild) {
            sum += root.val;
        }

        sum = dfsForLeftLeaves(root.left, true, sum);
        sum = dfsForLeftLeaves(root.right, false, sum);
        return sum;
    }

    /*** SWD Soln ***/
    public int sumOfLeftLeaves2(TreeNode root) {
        return dfsForLeftLeaves2(root, false);
    }

    private int dfsForLeftLeaves2(TreeNode root, boolean isLeftChild) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) {
            return isLeftChild ? root.val : 0;
        }

        int leftAns = dfsForLeftLeaves2(root.left, true);
        int rightAns = dfsForLeftLeaves2(root.right, false);

        return leftAns + rightAns;
    }
}
