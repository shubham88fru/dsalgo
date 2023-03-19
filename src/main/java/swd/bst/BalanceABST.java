package swd.bst;

import java.util.ArrayList;
import java.util.List;

//@link - https://leetcode.com/problems/balance-a-binary-search-tree/description/
public class BalanceABST {
    public TreeNode balanceBST(TreeNode root) {
        List<TreeNode> inorder = new ArrayList<>();

        //First, find the inorder traversal of the given bst.
        //For BST, inorder traversal will be sorted.
        traverseInorder(root, inorder);

        //Once we have the sorted array, use algorithm to
        //converst sorted array to a balanced bst.
        return sortedListToBalancedBST(inorder, 0, inorder.size()-1);
    }

    //Inorder traversal algorithm.
    private void traverseInorder(TreeNode root, List<TreeNode> ans) {
        if (root == null) return;

        traverseInorder(root.left, ans);
        ans.add(root);
        traverseInorder(root.right, ans);
    }

    //algorithm to converst a sorted array(list) to a balanced bst.
    private TreeNode sortedListToBalancedBST(List<TreeNode> inorder, int startIdx, int endIdx) {
        if (startIdx > endIdx) return null;

        //start from mid (this will ensure that left and right subtree will differ by max one node)
        int midIdx = (startIdx + endIdx) / 2;


        TreeNode root = inorder.get(midIdx);
        //start index for left subtree remains same, but end boundary will
        //be mid - 1.
        root.left =  sortedListToBalancedBST(inorder, startIdx, midIdx-1);

        //end boundary for right subtree remains same, but left boundary will
        //be mid + 1
        root.right = sortedListToBalancedBST(inorder, midIdx+1, endIdx);

        return root;
    }
}
