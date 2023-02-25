package swd.trees;

import java.util.List;

//@link - https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iv/
//@link - https://zhenchaogan.gitbook.io/leetcode-solution/leetcode-1676-lowest-common-ancestor-of-a-binary-tree-iv
public class LowestCommonAncestorInBTIV {

    public TreeNode lowestCommonAncestorIV(TreeNode root, List<TreeNode> nodes) {
        if (nodes.size() < 2) return null; //need atleast two nodes in the list.
        TreeNode currLCA = searchInSubTree(root, nodes.get(0), nodes.get(1)); //start with lca of first 2.

        for (int i = 2; i < nodes.size(); i++) {
            currLCA = searchInSubTree(root, currLCA, nodes.get(i));
        }
        return currLCA;
    }
    private TreeNode searchInSubTree(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;

        if (root.val == p.val || root.val == q.val) return root;

        TreeNode left = searchInSubTree(root.left, p, q);

        TreeNode right = searchInSubTree(root.right, p, q);

        if (left != null && right != null) return root;

        //Note this works for this question, because
        //this variation of the question guarantees that
        //p and q certainly exist in the bt.
        if (left != null) return left;
        if (right != null) return right;

        return null;
    }
}
