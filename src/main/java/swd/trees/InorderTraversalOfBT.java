package swd.trees;

import java.util.ArrayList;
import java.util.List;

//@link - https://leetcode.com/problems/binary-tree-inorder-traversal/
public class InorderTraversalOfBT {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        traverseInOrder(root, ans);
        return ans;
    }

    //Recursive algorithm for inorder traversal
    private void traverseInOrder(TreeNode root, List<Integer> ans) {
        //base case
        if (root == null) return;

        //Inorder traversal is -> left - root - right
        traverseInOrder(root.left, ans);
        ans.add(root.val);
        traverseInOrder(root.right, ans);
    }
}
