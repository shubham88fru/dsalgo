package lc_potd;

import java.util.ArrayDeque;
import java.util.Deque;

//@link - https://leetcode.com/problems/reverse-odd-levels-of-binary-tree/
//@check - https://www.youtube.com/watch?v=kHckQekd-LU&ab_channel=codestorywithMIK
public class ReverseOddLevelsOfBinaryTree {
    public TreeNode reverseOddLevels(TreeNode root) {
        // return bfs(root);

        dfs(root.left, root.right, 1);
        return root;
    }

    /*
        Following is my BFS solution.
    */
    private TreeNode bfs(TreeNode root) {
        Deque<Integer> stack = new ArrayDeque<>();
        Deque<TreeNode> q = new ArrayDeque<>();

        q.addLast(root);

        int level = 0;
        while (!q.isEmpty()) {
            int sz = q.size();
            while (sz > 0) {
                TreeNode node = q.removeFirst();
                if (level%2 == 0) {
                    if (node.left != null) {
                        stack.addFirst(node.left.val);
                        q.addLast(node.left);
                    }

                    if (node.right != null) {
                        stack.addFirst(node.right.val);
                        q.addLast(node.right);
                    }
                } else {
                    node.val = stack.removeFirst();
                    if (node.left != null) {
                        q.addLast(node.left);
                    }

                    if (node.right != null) {
                        q.addLast(node.right);
                    }
                }

                sz -= 1;

            }

            level += 1;
        }

        return root;
    }

    /*
        DFS code (coded by me) based on mik's
        explanation.
    */
    private void dfs(TreeNode left, TreeNode right, int level) {
        if (left == null) return ;
        if (right == null) return ;

        if (level%2 == 1) {
            int temp = left.val;
            left.val = right.val;
            right.val = temp;
        }

        //a recursive leap of faith, which is very hard to
        //digest.
        dfs(left.left, right.right, level + 1);
        dfs(left.right, right.left, level + 1);
    }
}
