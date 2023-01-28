package swd.trees;

//@link - https://leetcode.com/problems/cousins-in-binary-tree/description/
public class CousinsInBT {
    public boolean isCousins(TreeNode root, int x, int y) {
        String[] depthAndParent1 = dfs(root, x, 0, null).split("-");
        String[] depthAndParent2 = dfs(root, y, 0, null).split("-");

        //if x's parent is not same as y's parent and
        //depth of x is same as dept of y, then x and y are cousins.
        return (depthAndParent1[0].equals(depthAndParent2[0])
                && !depthAndParent1[1].equals(depthAndParent2[1]));
    }

    //Pre-order traversal algorithm
    private String dfs(TreeNode root, int val, int depth, TreeNode parent) {
        if (root == null) return null;
        if (root.val == val) {
            return (depth + "-" + parent);
        }

        String left = dfs(root.left, val, depth+1, root);
        if (left != null) return left;

        String right = dfs(root.right, val, depth+1, root);
        if (right != null) return right;

        return null;
    }
}
