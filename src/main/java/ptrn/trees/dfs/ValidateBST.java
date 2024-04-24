package ptrn.trees.dfs;

//@link - https://leetcode.com/problems/validate-binary-search-tree/
//@strvr - https://www.youtube.com/watch?v=f-sj7I5oXEI&ab_channel=takeUforward
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6589436294070272
public class ValidateBST {
    public boolean isValidBST(TreeNode root) {
        //using long min and max instead of int min and max.
        //since node themselves can have int min and max values.
        return isBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    //Keep a range for each node between which the values can lie.
    private boolean isBST(TreeNode root, long min, long max) {
        if (root == null) return true;
        if (root.left == null && root.right == null) return (root.val > min && root.val < max);

        //when moving to left child, value can be in (min, root.val)
        boolean left = isBST(root.left, min, root.val);
        if (!left) return false;

        //when moving to right child, value can be in (root.val, max)
        boolean right = isBST(root.right, root.val, max);
        if (!right) return false;

        //else valid if left is bst, right is bst and value of curr node is in range.
        return left && right && (root.val > min && root.val < max);
    }
}
