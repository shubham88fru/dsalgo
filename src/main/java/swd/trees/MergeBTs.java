package swd.trees;


//@link - https://leetcode.com/problems/merge-two-binary-trees/
public class MergeBTs {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        return mergeBTs(root1, root2);
    }

    private TreeNode mergeBTs(TreeNode root1, TreeNode root2) {
        //if leaf node of both tree, no further subtree possible. return.
        if (root1 == null && root2 == null) return null;

        //if left child (root1) is null (and right child (root2) is not null),
        //return right subtree.
        if (root1 == null) return root2;

        //if right child (root2) is null (and left child (root1) is not null),
        //return left subtree.
        if (root2 == null) return root1;

        //If none of above condition meet - Traverse in-order.
        TreeNode tree = new TreeNode((root1.val + root2.val), //sum the root nodes
                mergeBTs(root1.left, root2.left), //recursively visit left child
                mergeBTs(root1.right, root2.right) //recursively visit right child.
        );
        return tree;
    }
}
