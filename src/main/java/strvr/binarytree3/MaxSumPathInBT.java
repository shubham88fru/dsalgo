package strvr.binarytree3;

//@link - https://leetcode.com/problems/binary-tree-maximum-path-sum/
//@strvr - https://takeuforward.org/data-structure/maximum-sum-path-in-binary-tree/
public class MaxSumPathInBT {
    private int sum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        maxSumInBT(root);
        return sum;
    }

    private int maxSumInBT(TreeNode root) {
        if (root == null) return 0;

        //ignoring -ve sum from any branch.
        int left = Math.max(0, maxSumInBT(root.left));
        int right = Math.max(0, maxSumInBT(root.right));

        int entireSubTree = root.val + left + right;
        sum = Math.max(sum, entireSubTree);

        //At each node, take the branch that gives max sum
        //after considering current node.
        return root.val + Math.max(left, right);
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