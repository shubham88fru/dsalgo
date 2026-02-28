package lc_potd;

import java.util.*;

//@link - https://leetcode.com/problems/amount-of-time-for-binary-tree-to-be-infected/?
public class AmountOfTimeForBinaryTreeToBeInfected {
    public int amountOfTime(TreeNode root, int start) {
        return pass1(root, start);
    }

    /*
        My sol
     */
    private int pass1(TreeNode root, int start) {
        Map<TreeNode, TreeNode> parents = new HashMap<>();
        TreeNode[] sn = {null};
        fillParents(root, parents, sn, start);

        Deque<TreeNode> q = new ArrayDeque<>();
        q.addLast(sn[0]);
        int time = bfs(q, parents, new HashSet<>());

        return time-1;
    }

    private int bfs(Deque<TreeNode> q, Map<TreeNode, TreeNode> parents, Set<TreeNode> visited) {
        int time = 0;
        while (!q.isEmpty()) {
            int sz = q.size();
            while (sz > 0) {
                TreeNode node = q.removeFirst();
                visited.add(node);

                if (node.left != null && !visited.contains(node.left)) q.addLast(node.left);
                if (node.right != null && !visited.contains(node.right)) q.addLast(node.right);
                if (parents.containsKey(node) && !visited.contains(parents.get(node))) q.addLast(parents.get(node));

                sz -= 1;
            }

            time += 1;
        }

        return time;
    }

    private void fillParents(TreeNode root, Map<TreeNode, TreeNode> parents, TreeNode[] sn, int start) {
        if (root == null) return;

        if (root.val == start) {
            sn[0] = root;
        }

        if (root.left != null) parents.put(root.left, root);
        if (root.right != null) parents.put(root.right, root);

        fillParents(root.left, parents, sn, start);
        fillParents(root.right, parents, sn, start);
    }
}
