package strvr.bst2;

//@link - https://leetcode.com/problems/maximum-sum-bst-in-binary-tree/
//@strvr - not solved, but on very similar lines to Largest bst in bt.
public class MaxSumBSTInBT {
    int maxSum = 0;
    public int maxSumBST(TreeNode root) {
        bstWithMaxSum(root);
        return maxSum > 0 ? maxSum: 0;
    }

    private BSTData bstWithMaxSum(TreeNode root) {
        if (root == null) return new BSTData(0, Integer.MAX_VALUE, Integer.MIN_VALUE, 0);

        BSTData left = bstWithMaxSum(root.left);
        BSTData right = bstWithMaxSum(root.right);

        //if curr node's value is more than max in left
        //and less than min in right, and we know that left and right
        //are bst's because there min and max are marked accordingly (read comments below),
        //then left, right and curr will form a bst too.
        if (root.val > left.max && root.val < right.min) {
            //left,right and curr form a valid bst.
            int size = 1+left.size+right.size; //left's size + right's size + curr node.
            int sum = left.sum + right.sum + root.val;
            maxSum = Math.max(maxSum, sum);
            return new BSTData(size, Math.min(root.val, left.min),
                    Math.max(root.val, right.max), sum);
        } else {
            //left, right and curr don't form a valid bst.
            //In this case, we'll still keep track for the size of valid
            //bst that we have seen till now (so that we can return it later)
            //but for min and max, will mark min as INT_MIN and INT_MAX respectively
            //just that we ensure that when the parent tries to do the comparision
            //for min and max, it will fail and won't be able to form a bst with current
            //subtree. i.e. when parent compares its value with left's max, the check will
            //certainly fail because left's max is INT_MAX and nothing can be greater than that,
            //while when it compares its value with right's min, it will fail again since
            //right's min is INT_MIN and nothing can be smaller than that. Effectively, the
            //parent' won't be able to make a bst with such a subtree.
            //Note: we set the min and max to lowest and highest to mark this tree invalid bst,
            //but we still carry the max bst size that we have seen till now.
            return new BSTData(Math.max(left.size, right.size), Integer.MIN_VALUE, Integer.MAX_VALUE, Math.max(left.sum, right.sum));
        }
    }
}
