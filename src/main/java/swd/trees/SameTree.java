package swd.trees;

import java.util.ArrayList;
import java.util.List;

public class SameTree {
    /*** My Soln - Works but poorer TC as compared to SWD ***/
    public boolean isSameTree(TreeNode p, TreeNode q) {
        List<Integer> preorder1 = new ArrayList<>();
        List<Integer> preorder2 = new ArrayList<>();

        //get preorder of tree 1
        preOrder(p, preorder1);

        //get preorder of tree 2
        preOrder(q, preorder2);

        //if preorder same means both trees same.
        return preorder1.equals(preorder2);
    }

    //algorithm for preorder traversal of a binary tree.
    private void preOrder(TreeNode root, List<Integer> lst) {
        if (root == null) {
            lst.add(Integer.MIN_VALUE);
            return;
        }
        //print root
        lst.add(root.val);

        //then recursively left subtree
        preOrder(root.left, lst);

        //then recursively right subtree
        preOrder(root.right, lst);
    }

    /*** SWD Soln ***/
    public boolean isSameTree2(TreeNode p, TreeNode q) {
        return dfsForSameTree(p, q);
    }

    private boolean dfsForSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;

        if (p == null) {
            return false;
        }

        if (q == null) {
            return false;
        }

        boolean valssame = (p.val == q.val);

        //value of nodes same and left tree is same for both
        boolean leftTree = valssame && dfsForSameTree(p.left, q.left);

        //value of nodes same and right tree is same for both
        boolean rightTree = valssame && dfsForSameTree(p.right, q.right);

        return (leftTree && rightTree);
    }

}
