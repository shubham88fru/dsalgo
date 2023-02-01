package swd.trees;

import java.util.*;

//@link - https://leetcode.com/problems/binary-tree-postorder-traversal/
public class PostorderTraversalOfBT {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        traversePostOrderIterative(root, ans);
        // traversePostOrder(root, ans);
        return ans;
    }
    //1) Recursive algorithm
    private void traversePostOrder(TreeNode root, List<Integer> ans) {
        if (root == null) return;
        traversePostOrder(root.left, ans);
        traversePostOrder(root.right, ans);
        ans.add(root.val);
    }


    //2) Iterative algorithm
    private void traversePostOrderIterative(TreeNode root, List<Integer> ans) {
        if (root == null) return;
        Deque<TreeNode> stack = new ArrayDeque<>();

        stack.addFirst(root);

        //Trick: process in reverse order of an actual postorder traversal
        //i.e. Node - Right - Left
        while (!stack.isEmpty()) {
            TreeNode currNode = stack.removeFirst();
            ans.add(currNode.val);
            if (currNode.left != null) stack.addFirst(currNode.left);
            if (currNode.right != null) stack.addFirst(currNode.right);
        }

        //Finally reverse the list.
        Collections.reverse(ans);
    }
}
