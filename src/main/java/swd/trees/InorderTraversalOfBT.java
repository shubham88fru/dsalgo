package swd.trees;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

//@link - https://leetcode.com/problems/binary-tree-inorder-traversal/
public class InorderTraversalOfBT {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        //traverseInOrder(root, ans);
        traverseInOrderIterative(root, ans);
        return ans;
    }

    //1) Recursive algorithm for inorder traversal
    private void traverseInOrder(TreeNode root, List<Integer> ans) {
        //base case
        if (root == null) return;

        //Inorder traversal is -> left - root - right
        traverseInOrder(root.left, ans);
        ans.add(root.val);
        traverseInOrder(root.right, ans);
    }

    //2) Iterative algorithm for inorder traversal.
    private void traverseInOrderIterative(TreeNode root, List<Integer> ans) {
        if (root == null) return;

        Deque<TreeNode> stack = new ArrayDeque<>();

        stack.addFirst(root);

        //Push all left children to stack.
        pushAllLeftChildrenToStack(root, stack);

        //repeat till stack isn't empty.
        while (!stack.isEmpty()) {
            //Pop the leaf node.
            TreeNode currNode = stack.removeFirst();

            //Process it.
            ans.add(currNode.val);

            //If right child, push it to stack.
            //and then push all left children of this right child
            //to stack.
            TreeNode right = currNode.right;
            if (right != null) {
                stack.addFirst(right);
                pushAllLeftChildrenToStack(right, stack);
            }
        }
    }

    //Pushes all left children of a given node to the given stack.
    private void pushAllLeftChildrenToStack(TreeNode currNode, Deque<TreeNode> stack) {
        TreeNode left = currNode.left;
        while (left != null) {
            stack.addFirst(left);
            left = left.left;
        }
    }
}


class Test2 {
    public static void main(String[] args) {

        InorderTraversalOfBT inorderTraversalOfBT
                = new InorderTraversalOfBT();
        TreeNode rightleft = new TreeNode(3);
        TreeNode right = new TreeNode(2, rightleft, null);
        TreeNode root = new TreeNode(1, null, right);

        System.out.println(inorderTraversalOfBT.inorderTraversal(root));
    }
}
