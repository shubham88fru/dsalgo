package strvr.bst2;

import java.util.ArrayDeque;
import java.util.Deque;

//@link - https://leetcode.com/problems/binary-search-tree-iterator/
//@strvr - https://www.youtube.com/watch?v=D2jMcmxU4bs&ab_channel=takeUforward
public class BSTIterator {
    //The solution to this problem lies in
    //mimicking the recursive stack of a inorder traversal.
    //We'll use a custom stack and mimick the recursive stack.
    //Just imagine how the line moves when we write the inorder traversal
    //of a tree.
    Deque<TreeNode> s = new ArrayDeque<>();

    public BSTIterator(TreeNode root) {
        //fill the stack with all the left child to begin with.
        fill(root);
    }

    public int next() {
        if (hasNext()) {
            return getNext();
        }
        return -1;
    }

    public boolean hasNext() {
        return !s.isEmpty();
    }

    private void fill(TreeNode root) {
        while (root != null) {
            s.addFirst(root);
            root = root.left;
        }
    }

    //pop from stack (as would happen in a recursive stack)
    //and if the top element has right child, push all the right
    //child's left children to stack.
    private int getNext() {
        //our answer for this call however, will still be
        //the current top of stack (not the top after pushing right's left)
        TreeNode ansNode = s.removeFirst();
        if (ansNode.right != null) {
            fill(ansNode.right);
        }
        return ansNode.val;
    }
}
