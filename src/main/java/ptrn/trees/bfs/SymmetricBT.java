package ptrn.trees.bfs;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;

//@link - https://leetcode.com/problems/symmetric-tree/description/
//@strvr - https://takeuforward.org/data-structure/check-for-symmetrical-binary-tree/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/4860721692934144
public class SymmetricBT {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        // return dfsForSameTree(root.left, root.right);
        return bfs(root);
    }

    //1) DFS Soln: Traverese pre-order.
    private boolean dfsForSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;

        if (p == null) {
            return false;
        }

        if (q == null) {
            return false;
        }

        boolean valssame = (p.val == q.val);

        //value of nodes same and left tree of p is same as right tree of q.
        boolean leftTree = valssame && dfsForSameTree(p.left, q.right);

        //value of nodes same and right tree of p is same as left tree of q.
        boolean rightTree = valssame && dfsForSameTree(p.right, q.left);

        return (leftTree && rightTree);
    }

    //2) BFS Soln.
    private boolean bfs(TreeNode root) {
        Deque<Optional<TreeNode>> q = new ArrayDeque<>();

        q.addLast(Optional.ofNullable(root.left));
        q.addLast(Optional.ofNullable(root.right));

        while (!q.isEmpty()) {
            Optional<TreeNode> leftO = q.removeFirst();
            Optional<TreeNode> rightO = q.removeFirst();
            TreeNode left = leftO.orElse(null);
            TreeNode right = rightO.orElse(null);

            if (left == null && right == null) continue;
            if (left == null || right == null) return false; //asymmetric.
            if (left.val != right.val) return false; //asymmetric.

            //left's left should be same as right's right.
            //so enque them together, so that they get dequed together.
            q.addLast(Optional.ofNullable(left.left));
            q.addLast(Optional.ofNullable(right.right));

            //left's right should be same as right's left.
            //so enque them together, so that they get dequed together.
            q.addLast(Optional.ofNullable(left.right));
            q.addLast(Optional.ofNullable(right.left));
        }

        return true;
    }
}
