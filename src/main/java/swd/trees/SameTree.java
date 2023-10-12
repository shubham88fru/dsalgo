package swd.trees;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

//@link - https://leetcode.com/problems/same-tree/description/
public class SameTree {
    /*** My Soln - Works but poorer TC as compared to SWD
     * Also, my approach is wrong. It is not always true that
     * if preorder traversal of trees are same then trees are similar.
     * eg:
     *Tree1:     1
     *          /
     *         2
     *        /
     *       3
     *
     *Tree2:      1
     *           / \
     *          2   3
     *
     * Above trees have same preorder traversal but aren't same.
     *
     * ***/
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
        return dfsForSameTreeDFS(p, q);
    }

    //1) DFS Algorithm to solve the problem.
    private boolean dfsForSameTreeDFS(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;

        if (p == null) {
            return false;
        }

        if (q == null) {
            return false;
        }

        boolean valssame = (p.val == q.val);
        boolean leftTree = valssame && dfsForSameTreeDFS(p.left, q.left);
        boolean rightTree = valssame && dfsForSameTreeDFS(p.right, q.right);

        return (leftTree && rightTree);
    }

    //2) BFS Algorithm to solve the problem (My algo)
    private boolean dfsForSameTreeBFS(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;

        if (p == null) return false;
        if (q == null) return false;

        Deque<TreeNode> queueP = new ArrayDeque<>();
        queueP.addLast(p);

        Deque<TreeNode> queueQ = new ArrayDeque<>();
        queueQ.addLast(q);

        while(!queueP.isEmpty() && !queueQ.isEmpty()) {
            TreeNode currNodeP = queueP.removeFirst();
            TreeNode currNodeQ = queueQ.removeFirst();

            if (currNodeP.val != currNodeQ.val) return false;

            if (currNodeP.left == null && currNodeQ.left != null) return false;
            if (currNodeQ.left == null && currNodeP.left != null) return false;

            if (currNodeP.right == null && currNodeQ.right != null) return false;
            if (currNodeQ.right == null && currNodeP.right != null) return false;


            if (currNodeP.left != null) queueP.addLast(currNodeP.left);

            if (currNodeP.right != null) queueP.addLast(currNodeP.right);

            if (currNodeQ.left != null) queueQ.addLast(currNodeQ.left);

            if (currNodeQ.right != null) queueQ.addLast(currNodeQ.right);

        }

        if (!queueP.isEmpty()) return false;
        if (!queueQ.isEmpty()) return false;

        return true;
    }

    //2) BFS Algorithm to solve the problem (SWD algo)
    private boolean dfsForSameTreeBFSSWD(TreeNode proot, TreeNode qroot) {
        if (proot == null && qroot == null) return true;

        if (proot == null) return false;
        if (qroot == null) return false;

        Deque<NodePair> queue = new ArrayDeque<>();
        queue.addLast(new NodePair(proot, qroot));

        while (!queue.isEmpty()) {
            NodePair pair = queue.removeFirst();
            TreeNode p = pair.p;
            TreeNode q = pair.q;

            if (p == null && q != null) return false;
            if (q == null && p != null) return false;
            if (p == null && q == null) continue;
            if (p.val != q.val) return false;

            queue.addLast(new NodePair(p.left, q.left));
            queue.addLast(new NodePair(p.right, q.right));
        }

        return true;
    }
}

class NodePair {
    public TreeNode p;
    public TreeNode q;

    public NodePair(TreeNode p, TreeNode q) {
        this.p = p;
        this.q = q;
    }
}
