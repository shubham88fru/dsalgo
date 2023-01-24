package swd.trees;

import java.util.ArrayList;
import java.util.List;

//@link - https://leetcode.com/problems/binary-tree-postorder-traversal/
public class PostorderTraversalOfBT {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        traversePostOrder(root, ans);
        return ans;
    }

    private void traversePostOrder(TreeNode root, List<Integer> ans) {
        if (root == null) return;
        traversePostOrder(root.left, ans);
        traversePostOrder(root.right, ans);
        ans.add(root.val);

    }
}
