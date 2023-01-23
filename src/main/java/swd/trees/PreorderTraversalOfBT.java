package swd.trees;

import java.util.ArrayList;
import java.util.List;

//@link - https://leetcode.com/problems/binary-tree-preorder-traversal/description/
public class PreorderTraversalOfBT {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        preOrder(root, ans);
        return ans;
    }

    private void preOrder(TreeNode root, List<Integer> lst) {
        if (root == null) return;
        lst.add(root.val);
        preOrder(root.left, lst);
        preOrder(root.right, lst);
    }
}
