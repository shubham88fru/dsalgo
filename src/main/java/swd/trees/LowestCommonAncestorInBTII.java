package swd.trees;

import strvr.binarytree.TreeNode;

//@link - https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-ii/
//@Link - https://zhenchaogan.gitbook.io/leetcode-solution/leetcode-1644-lowest-common-ancestor-of-a-binary-tree-ii
public class LowestCommonAncestorInBTII {
    public TreeNode lowestCommonAncestorII(TreeNode root, TreeNode p, TreeNode q) {
        boolean[] found = {false, false}; //pfound and qfound.
        TreeNode ans = searchInSubTree(root, p, q, found);

        //if both p and q present, then the problem is same
        //as lca 1. Otherwise, have to return null.
        return (found[0] && found[1]) ?  ans : null;
    }


    private TreeNode searchInSubTree(TreeNode root, TreeNode p, TreeNode q, boolean[] found) {
        if (root == null) return null;

        //Note: using post order here, in contrast to lca 1.
        TreeNode left = searchInSubTree(root.left, p, q, found);
        TreeNode right = searchInSubTree(root.right, p, q, found);

        if (root.val == p.val) {
            found[0] = true;
            return root;
        }

        if (root.val == q.val) {
            found[1] = true;
            return root;
        }

        if (left != null && right != null) return root;

        if (left != null) return left;
        if (right != null) return right;

        return null;
    }
}
