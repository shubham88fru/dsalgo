package strvr.binarytree3;

import java.util.ArrayDeque;
import java.util.Deque;

//@link - https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
//@strvr - https://takeuforward.org/data-structure/flatten-binary-tree-to-linked-list/
public class FlattenBTToLL {
    TreeNode prev = null;
    public void flatten(TreeNode root) {
        // flattenToLLRecursive(root);
        flattenToLLIterative(root);
    }

    //1) Recurisve soln
    //This builds the list from end.
    private void flattenToLLRecursive(TreeNode root) {
        if (root == null) return;

        //notice - sort of a rever preorder traversal.
        flattenToLLRecursive(root.right); //right
        flattenToLLRecursive(root.left);//left
        root.right = prev;//root
        root.left = null;

        prev = root;
    }

    //2) Iterative solution
    //This builds the list from the front.
    private void flattenToLLIterative(TreeNode root) {
        if (root == null) return;

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.addFirst(root);
        while (!stack.isEmpty()) {
            TreeNode curr = stack.peekFirst();
            stack.removeFirst();

            if (curr.right != null) stack.addFirst(curr.right);
            if (curr.left != null) stack.addFirst(curr.left);

            if (!stack.isEmpty()) curr.right = stack.peekFirst();
            curr.left = null;
        }
    }
}
