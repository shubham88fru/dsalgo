package swd.trees;

import java.util.*;

//@link - https://leetcode.com/problems/n-ary-tree-postorder-traversal/description/
public class PostorderTraversalOfNAT {
    public List<Integer> postorder(Node root) {
        List<Integer> ans = new ArrayList<>();
        traversePostOrderIterative(root, ans);
        // traversePostOrder(root, ans);
        return ans;
    }

    //1) Recursive algorithm for post order traversal of n-ary tree.
    private void traversePostOrder(Node root, List<Integer> ans) {
        if (root == null) return;

        //visit all children.
        for (Node child: root.children) {
            traversePostOrder(child, ans);
        }

        //visit root.
        ans.add(root.val);
    }

    //2) Iterative algorithm
    private void traversePostOrderIterative(Node root, List<Integer> ans) {
        if (root == null) return;
        Deque<Node> stack = new ArrayDeque<>();
        stack.addFirst(root);

        while (!stack.isEmpty()) {
            Node currNode = stack.removeFirst();

            //Trick: process in reverse order of an actual postorder traversal
            //i.e. Node - Right - Left
            ans.add(currNode.val);
            for (Node child: currNode.children) {
                stack.addFirst(child);
            }
        }
        //Finally reverse the list.
        Collections.reverse(ans);
    }
}
