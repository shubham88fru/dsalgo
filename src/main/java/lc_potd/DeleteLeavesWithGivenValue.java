package lc_potd;

//@link - https://leetcode.com/problems/delete-leaves-with-a-given-value/
public class DeleteLeavesWithGivenValue {
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        Pair5 p =  dfs(root, target);
        if (p.delete) return null;
        return p.node;
    }

    private Pair5 dfs(TreeNode root, int target) {
        if (root == null) return new Pair5(null, true);
        if (root.left == null && root.right == null && root.val == target) return new Pair5(root, true);
        Pair5 left = dfs(root.left, target);
        Pair5 right = dfs(root.right, target);

        if (left.delete) root.left = null;
        if (right.delete) root.right = null;

        return new Pair5(root, left.delete && right.delete && root.val == target);
    }
}


class Pair5 {
    TreeNode node;
    boolean delete;
    public Pair5(TreeNode node, boolean delete) {
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