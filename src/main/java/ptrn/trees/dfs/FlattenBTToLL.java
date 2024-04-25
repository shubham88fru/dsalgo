package ptrn.trees.dfs;

import java.util.ArrayDeque;
import java.util.Deque;

//@link - https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
//@strvr - https://takeuforward.org/data-structure/flatten-binary-tree-to-linked-list/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6070771161235456
public class FlattenBTToLL {
    TreeNode prev = null;
    public void flatten(TreeNode root) {
        // flattenToLLRecursive(root);
        flattenToLLIterative(root);
    }

    //-1) Using queue. Do a preorder traversal and store
    //the nodes in a queue. Then dequeu all the nodes one by one
    //and store each nodes' right to the next node.
    //Con: Requires extra space.
    //Pro: Very straight forward and simplest.
    
    //0) Edctv solution - check edctv for a nice visualization.
    /**
     *Starting from the tree’s root, we traverse the tree in a depth-first search manner.
     * At each node, we check if it has a left child. If it does, we follow a path down to the
     * rightmost node of the left subtree. This can be achieved by repeatedly moving to the right
     * child of each node in the left subtree until we reach a node that does not have a right child.
     *
     * Once we reach the rightmost node, we point the right pointer of this node to the right child of the current node.
     * After making this connection, we point the current node’s right pointer to the current node’s left child.
     * Finally, we set the current node’s left pointer to NULL. We repeat this process until all nodes of the tree have been traversed.
     */
    private TreeNode flattenTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode current = root;
        while (current != null) {

            if (current.left != null) {

                TreeNode last = current.left;
                while (last.right != null) {
                    last = last.right;
                }

                last.right = current.right;
                current.right = current.left;
                current.left = null;

            }
            current = current.right;
        }
        return root;
    }

    //1) Recursive soln
    //This builds the list from end.
    private void flattenToLLRecursive(TreeNode root) {
        if (root == null) return;

        //notice - sort of a reverse preorder traversal.
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
