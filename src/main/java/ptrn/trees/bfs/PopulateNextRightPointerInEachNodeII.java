package ptrn.trees.bfs;

import java.util.ArrayDeque;
import java.util.Deque;

//@link - https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/
public class PopulateNextRightPointerInEachNodeII {

    /**
     * @see ptrn.trees.bfs.PopulateNextRightPointerInEachNode
     * Only diff between this (part II) and the above (part I) problem is that
     * in part I the tree is given to be a perfect and balanced tree.
     * And so, that condition saves us from a lot of hassle when following
     * solution 1 for part I. However, the solution 1, won't directly
     * work for this question, because nodes and children can be null in this part.
     * However, my BFS soln from part I will work as is for part II as well, because
     * of the way it works.
     */

    public LCNode connect(LCNode root) {
        bfs(root);
        return root;
    }

    //2) Better soln (not optimal) - Using standard BFS.
    private void bfs(LCNode root) {
        if (root == null) return;
        Deque<LCNode> q = new ArrayDeque<>();
        q.addLast(root);

        while (!q.isEmpty()) {
            int sz = q.size();
            LCNode prev = q.removeFirst();
            sz -= 1;
            if (prev.left != null) q.addLast(prev.left);
            if (prev.right != null) q.addLast(prev.right);

            while (sz > 0) {
                LCNode curr = q.removeFirst();
                prev.next = curr;
                prev = curr;
                if (curr.left != null) q.addLast(curr.left);
                if (curr.right != null) q.addLast(curr.right);
                sz -= 1;
            }
        }
    }
}
