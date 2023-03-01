package swd.bst;

//@link - https://leetcode.com/problems/search-in-a-binary-search-tree/description/
public class SearchInBST {
    public TreeNode searchBST(TreeNode root, int val) {
        return searchBinary(root, val);
    }

    private TreeNode searchBinary(TreeNode root, int val) {
        if (root == null) return null;

        //if found return.
        if (root.val == val) return root;

        //if value less than root, then should be present (if at all)
        //in left subtree.
        //else should be present in right subtree.
        if (val < root.val) return searchBinary(root.left, val);
        else return searchBinary(root.right, val);
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
