package ptrn.trees.dfs;

//@link - https://leetcode.com/problems/binary-tree-maximum-path-sum/
//@strvr - https://takeuforward.org/data-structure/maximum-sum-path-in-binary-tree/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6299054360494080
public class MaxSumPathInBT {
    private int sum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        maxSumInBT(root);
        return sum;
    }


    //1) Strvr soln.
    private int maxSumInBT(TreeNode root) {
        if (root == null) return 0;

        //ignoring -ve sum from any branch.
        int left = Math.max(0, maxSumInBT(root.left));
        int right = Math.max(0, maxSumInBT(root.right));

        //entire subtree (excluding -ves)
        int entireSubTree = root.val + left + right;
        sum = Math.max(sum, entireSubTree);

        //At each node, return the branch sum that gives max sum
        //after considering current node.
        return root.val + Math.max(left, right);
    }

    //2) Edctv soln.
    public int maxContrib(TreeNode root) {
        if (root == null)
            return 0;

        int maxLeft = this.maxContrib(root.left);
        int maxRight = this.maxContrib(root.right);

        int leftSubtree = 0;
        int rightSubtree = 0;

        if (maxLeft > 0)
            leftSubtree = maxLeft;

        if (maxRight > 0)
            rightSubtree = maxRight;

        int valueNewpath = root.val + leftSubtree + rightSubtree;

        this.sum = Math.max(this.sum, valueNewpath);

        return root.val + Math.max(leftSubtree, rightSubtree);
    }
}