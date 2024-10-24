package lc_potd;

//@link - https://leetcode.com/problems/flip-equivalent-binary-trees/
public class FlipEquivalentBinaryTree {
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        return dfs(root1, root2);
    }

    //my solns.
    private boolean dfs(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null) return false;
        if (root1.val != root2.val) return false;

        //normal pre-order.
        boolean left = dfs(root1.left, root2.left); //left - left
        boolean right = dfs(root1.right, root2.right); //right - right

        //reverse'ish preorder.
        boolean swapLeft = dfs(root1.right, root2.left); //right - left (mimick swapping in tree1)
        boolean swapRight = dfs(root1.left, root2.right); //left - right (mimick swapping in tree1)

        //if either of (normal or reverse) preorder traversals return true, then possible.
        return (left && right) || ( swapLeft && swapRight);
    }

    private boolean dfsOptimized(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null) return false;
        if (root1.val != root2.val) return false;

        //normal pre-order.
        boolean left = dfs(root1.left, root2.left); //left - left
        boolean right = false; //optimization, if for normal preorder - left is true then only check right.
        if (left) right = dfs(root1.right, root2.right); //right - right

        //reverse'ish preorder.
        boolean swapLeft = dfs(root1.right, root2.left); //right - left (mimick swapping in tree1)
        boolean swapRight = false; //optimization, if for reversed preorder - left is true then only check right.
        if (swapLeft) swapRight = dfs(root1.left, root2.right); //left - right (mimick swapping in tree1)

        //if either of (normal or reverse) preorder traversals return true, then possible.
        return (left && right) || (swapLeft && swapRight);
    }
}
