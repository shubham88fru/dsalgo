package lc_challenges;

//@link - https://leetcode.com/problems/delete-leaves-with-a-given-value/
public class DeleteLeavesWithGivenValue {
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        PairData p =  dfs(root, target);
        if (p.delete) return null;
        return p.node;
    }

    private PairData dfs(TreeNode root, int target) {
        if (root == null) return new PairData(null, true);
        if (root.left == null && root.right == null && root.val == target) return new PairData(root, true);
        PairData left = dfs(root.left, target);
        PairData right = dfs(root.right, target);

        if (left.delete) root.left = null;
        if (right.delete) root.right = null;

        return new PairData(root, left.delete && right.delete && root.val == target);
    }
}


class PairData {
    TreeNode node;
    boolean delete;
    public PairData(TreeNode node, boolean delete) {
        this.node = node;
        this.delete = delete;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}