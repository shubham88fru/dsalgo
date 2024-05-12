package ptrn.trees.dfs;

//@link - https://leetcode.com/problems/validate-binary-search-tree/
//@strvr - https://www.youtube.com/watch?v=f-sj7I5oXEI&ab_channel=takeUforward
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5951094676783104
public class ValidateBST {

    //1) Strvr soln
    /********************************************************************/
    public boolean isValidBST1(TreeNode root) {
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
    /********************************************************************/

    //2) My soln.
    /********************************************************************/
    public boolean isValidBST2(TreeNode root) {
        return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE).bst;
    }


    private TraversalData dfs(TreeNode root, long min, long max) {
        if (root == null) return new TraversalData(true, Long.MAX_VALUE, Long.MIN_VALUE);
        if (root.left == null && root.right == null) {
            return new TraversalData(true, root.val, root.val);
        }

        TraversalData left = dfs(root.left, min, max);
        TraversalData right = dfs(root.right, min, max);

        if (!left.bst || !right.bst) return new TraversalData(false, -1, -1);

        if (root.val > left.max && root.val < right.min) {
            return new TraversalData(true,
                    Math.min(Math.min(left.min, root.val), right.min),
                    Math.max(Math.max(left.max, root.val), right.max)
            );
        }

        return new TraversalData(false, -1, -1);
    }
    /********************************************************************/

    //3) Edctv soln. Do an inorder traversal. If valid bst, inorder should be sorted.
    /********************************************************************/
    public boolean isValidBST3(TreeNode root) {
        long[] prev = {Long.MIN_VALUE};
        return dfs2(root, prev);
    }

    private boolean dfs2(TreeNode root, long[] prev) {
        if (root == null)
            return true;

        if (!dfs2(root.left, prev))
            return false;

        if (root.val <= prev[0])
            return false;

        prev[0] = root.val;

        return dfs2(root.right, prev);
    }
    /********************************************************************/
}

class TraversalData {
    boolean bst;
    long min;
    long max;
    public TraversalData(boolean bst, long min, long max) {
        this.bst = bst;
        this.min = min;
        this.max = max;
    }
}
