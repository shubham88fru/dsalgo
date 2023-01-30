package swd.trees;

import java.util.*;

//@link - https://leetcode.com/problems/n-ary-tree-preorder-traversal/solutions/
public class PreorderTraversalOfNAT {
    public List<Integer> preorder(Node root) {
        List<Integer> ans = new ArrayList<>();
        //preorderNAT(root, ans);
        preorderNATIterative(root, ans);
        return ans;
    }

    //1) Recursive soln.
    //Recursive algorithm for preorder traversal of N-ary tree.
    private void preorderNAT(Node root, List<Integer> ans) {
        //Base case.
        if (root == null) return;

        //visit root.
        ans.add(root.val);

        //visit all children from left to right.
        for (Node child: root.children) {
            preorderNAT(child, ans);
        }
    }

    //2) Iterative approach
    private void preorderNATIterative(Node root, List<Integer> ans) {
        if (root == null) return;

        //Initialize a stack.
        Deque<Node> stack = new ArrayDeque<>();

        stack.addFirst(root);

        //continue till stack is not empty
        while (!stack.isEmpty()) {
            //pop top node in stack.
            Node currNode = stack.removeFirst();
            ans.add(currNode.val);

            //iterate over curr node's children in reverse order (right to left)
            //so that we push the left most child at the top, so that
            //in next iteration, leftmost child get picked up - Preoder traversal.
            ListIterator<Node> listIterator = currNode.children.listIterator(currNode.children.size());
            while (listIterator.hasPrevious()) {
                Node prev = (Node)listIterator.previous();
                if (prev != null) stack.addFirst(prev);
            }
        }
    }
}
