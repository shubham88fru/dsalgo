package swd.trees;

//@link - https://leetcode.com/problems/maximum-depth-of-binary-tree/description/
public class HeightOfBT {
    public int maxDepth(TreeNode root) {
        //Max depth of BT is nothing but its height.
        return heightOfBT(root);
    }

    //Recursive algorithm to find height of BT.
    private int heightOfBT(TreeNode root) {
        //Base case. If already on a leaf node, no height.
        if (root == null) return 0;

        //height of left sub tree from a node.
        int left = 1 + heightOfBT(root.left);

        //height of right sub tree from a node.
        int right = 1 + heightOfBT(root.right);

        //find the max.
        return Math.max(left, right);
    }
}


/**
 * Definition for a binary tree node.
 **/
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
