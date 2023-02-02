package swd.trees;

//@link - https://leetcode.com/problems/univalued-binary-tree/description/
public class UnivaluedBT {
    public boolean isUnivalTree(TreeNode root) {
        return isUnivalued(root, root.val);
    }

    private boolean isUnivalued(TreeNode root, int val) {
        //If reached till leaf and we couldn't find a node which is un-matching, return true
        if (root == null) return true;

        //traverse preorder and check if
        //curr nodes val is same as root's val and left subtree and right subtree are univalued.
        return (val == root.val) && isUnivalued(root.left, val) && isUnivalued(root.right, val);
    }
}
