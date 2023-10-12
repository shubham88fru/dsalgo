package strvr.binarytree;

//@link - https://leetcode.com/problems/balanced-binary-tree/
//@strvr - https://takeuforward.org/data-structure/check-if-the-binary-tree-is-balanced-binary-tree/
public class BalancedBinaryTree {
    public boolean isBalanced(TreeNode root) {
        //return checkIfBalancedBT(root);
        return (checkIfBalancedBTOptimal(root) != -1);
    }

    //1) Optimal solution
    //T: O(N)
    private int checkIfBalancedBTOptimal(TreeNode root) {
        if (root == null) return 0;

        int lh = checkIfBalancedBTOptimal(root.left);
        if (lh == -1) return -1;
        int rh = checkIfBalancedBTOptimal(root.right);
        if (rh == -1) return -1;

        if (Math.abs(lh-rh) > 1) return -1;
        return (Math.max(lh, rh) + 1); //for a node -> node to child link + whichever child is deeper.
    }



    //2) Brute force
    //T: O(N^2) - O(N) for height and O(N) for the recursive func.
    private boolean checkIfBalancedBTBrute(TreeNode root) {
        if (root == null) return true;

        int lh = heightOfBTDFS(root.left);
        int rh = heightOfBTDFS(root.right);
        if (Math.abs(lh-rh) > 1) return false;
        return checkIfBalancedBTBrute(root.left) && checkIfBalancedBTBrute(root.right);
    }

    private int heightOfBTDFS(TreeNode root) {
        //Base case. If already on a leaf node, no height.
        if (root == null) return 0;

        //height of left sub tree from a node.
        int left = 1 + heightOfBTDFS(root.left);

        //height of right sub tree from a node.
        int right = 1 + heightOfBTDFS(root.right);

        //find the max.
        return Math.max(left, right);
    }
}
