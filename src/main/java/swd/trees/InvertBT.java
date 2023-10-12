package swd.trees;


//@link - https://leetcode.com/problems/invert-binary-tree/
public class InvertBT {

    /*** My Soln - Works but nasty ***/
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        dfsToInvert(root);
        return root;
    }

    private TreeNode dfsToInvert(TreeNode root) {
        if (root == null) return null;

        TreeNode temp;
        temp = root.left;
        root.left = root.right;
        root.right = temp;

        if (root.left != null)
            dfsToInvert(root.left);

        if (root.right != null)
            dfsToInvert(root.right);

        return root;
    }

    /*** SWD Soln ***/
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) return null;
        TreeNode leftAns = invertTree2(root.left);
        TreeNode rightAns = invertTree2(root.right);

        root.left = rightAns;
        root.right = leftAns;

        return root;
    }
}

class Test {
    public static void main(String[] args) {
        InvertBT invertBT = new InvertBT();
        TreeNode left = new TreeNode(2);
        TreeNode root = new TreeNode(1, left, null);
        invertBT.invertTree(root);
        System.out.println(root);
    }
}
