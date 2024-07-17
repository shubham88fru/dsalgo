package lc_potd;

import java.util.*;
import java.util.stream.Collectors;

//@link - https://leetcode.com/problems/delete-nodes-and-return-forest/description/
public class DeleteNodesAndReturnForest {

    //My soln. Feels more intuitive to me.
    //Aryan's soln is kinda confusing.
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> ans = new ArrayList<>();
        Deque<TreeNode> orphans = new ArrayDeque<>();
        Set<Integer> toDelete = Arrays.stream(to_delete).mapToObj(i -> i)
                .collect(Collectors
                        .toCollection(HashSet::new));

        orphans.addLast(root);
        while (!orphans.isEmpty()) {
            TreeNode rt = orphans.removeFirst();
            markAndSweep(rt, orphans, toDelete);
            if (rt != null && rt.val != -1) ans.add(rt);
        }
        return ans;
    }

    private boolean markAndSweep(TreeNode root, Deque<TreeNode> orphans, Set<Integer> toDelete) {
        if (root == null) return false;
        if (toDelete.contains(root.val)) {
            if (root.left != null) orphans.addLast(root.left);
            if (root.right != null) orphans.addLast(root.right);
            root.val = -1;
            return true;
        }

        boolean deleteLeft = markAndSweep(root.left, orphans, toDelete);
        if (deleteLeft) root.left = null;

        boolean deleteRight = markAndSweep(root.right, orphans, toDelete);
        if (deleteRight) root.right = null;

        return false;
    }
}
