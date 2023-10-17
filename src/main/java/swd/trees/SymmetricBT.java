package swd.trees;



//@link - https://leetcode.com/problems/symmetric-tree/description/
public class SymmetricBT {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return dfsForSameTree(root.left, root.right);
    }

    //Traverese pre-order.
    private boolean dfsForSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;

        if (p == null) {
            return false;
        }

        if (q == null) {
            return false;
        }

        boolean valssame = (p.val == q.val);

        //value of nodes same and left tree of p is same as right tree of q.
        boolean leftTree = valssame && dfsForSameTree(p.left, q.right);

        //value of nodes same and right tree of p is same as left tree of q.
        boolean rightTree = valssame && dfsForSameTree(p.right, q.left);

        return (leftTree && rightTree);
    }
}
