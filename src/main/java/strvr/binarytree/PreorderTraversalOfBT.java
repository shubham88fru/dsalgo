package strvr.binarytree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

//@link - https://leetcode.com/problems/binary-tree-preorder-traversal/description/
//@strvr - https://takeuforward.org/data-structure/preorder-traversal-of-binary-tree/
public class PreorderTraversalOfBT {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        //preOrder(root, ans);
        preOrderIterative(root, ans);
        return ans;
    }

    //Sol2: Recursive
    private void preOrder(TreeNode root, List<Integer> lst) {
        if (root == null) return;
        lst.add(root.val);
        preOrder(root.left, lst);
        preOrder(root.right, lst);
    }

    //Sol1: Iterative
    private void preOrderIterative(TreeNode root, List<Integer> lst) {
        if (root == null) return;

        //Initialize a stack.
        Deque<TreeNode> stack = new ArrayDeque<>();

        //put the root node to start with.
        stack.addFirst(root);

        //keep executing till stack isn't empty.
        while (!stack.isEmpty()) {
            //pop the top node from stack.
            TreeNode currNode = stack.removeFirst();

            //process the node.
            lst.add(currNode.val);

            //Note: (Counterintuitively?) push the right child
            //to the stack first and then the left child because
            //stack is LIFO and we want left to be processed before right
            //in a preorder traversal.
            if (currNode.right != null) stack.addFirst(currNode.right);
            if (currNode.left != null) stack.addFirst(currNode.left);
        }
    }
}
