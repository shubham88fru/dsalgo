package swd.bst;


import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/two-sum-iv-input-is-a-bst/description/
public class TwoSumIVBST {
    public boolean findTarget(TreeNode root, int k) {
        Map<Integer, Integer> seenmap = new HashMap<>();
        return pairPresent(root, k, seenmap);
    }

    private boolean pairPresent(TreeNode root, int k, Map<Integer, Integer> seenmap) {
        if (root == null) return false;

        if (seenmap.containsKey(k - root.val)) return true;
        if (!seenmap.containsKey(root.val)) seenmap.put(root.val, 1);
        boolean left = pairPresent(root.left, k, seenmap);

        if (left) return true;
        boolean right = pairPresent(root.right, k, seenmap);
        return right;
    }
}
