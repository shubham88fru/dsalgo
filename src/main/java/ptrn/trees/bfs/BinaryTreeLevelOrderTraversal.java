package ptrn.trees.bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

//@link - https://leetcode.com/problems/binary-tree-level-order-traversal/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/4874740880900096
public class BinaryTreeLevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        // return recursive(root);
        return iterative(root);
    }

    private List<List<Integer>> recursive(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(root, 0, ans);
        return ans;
    }

    private void dfs(TreeNode root, int d, List<List<Integer>> ans) {
        if (root == null) return;

        if (ans.size()-1 < d) ans.add(new ArrayList<>());
        ans.get(d).add(root.val);

        dfs(root.left, d+1, ans);
        dfs(root.right, d+1, ans);
    }

    private List<List<Integer>> iterative(TreeNode root) {
        return bfs(root);
    }

    private List<List<Integer>> bfs(TreeNode root) {

        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;

        Deque<TreeNode> q = new ArrayDeque<>();
        q.addLast(root);

        while (!q.isEmpty()) {
            int sz = q.size();
            List<Integer> lvl = new ArrayList<>();
            while (sz > 0) {
                TreeNode node = q.removeFirst();
                lvl.add(node.val);

                if (node.left != null) q.addLast(node.left);
                if (node.right != null) q.addLast(node.right);

                sz -= 1;
            }
            ans.add(lvl);
        }

        return ans;
    }
}
