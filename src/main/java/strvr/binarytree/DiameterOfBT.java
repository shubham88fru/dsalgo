package strvr.binarytree;

//@link - https://leetcode.com/problems/diameter-of-binary-tree/
//@strvr - https://takeuforward.org/data-structure/calculate-the-diameter-of-a-binary-tree/
public class DiameterOfBT {
    public int diameterOfBinaryTree(TreeNode root) {
        int[] max = {0};
        //maxDistBetweenTwoNodes(root, max);
        findMaxOptimal(root, max);
        return max[0];
    }

    //1) optimal solution
    int findMaxOptimal(TreeNode root, int[] max) {
        if (root == null) return 0;
        int lh = findMaxOptimal(root.left, max);
        int rh = findMaxOptimal(root.right, max);

        max[0] = Math.max(max[0], lh+rh);
        return 1 + Math.max(lh, rh); //to parent, add parent plus whichever child is deeper.
    }

    //2) Brute force.
    //keep track of left and right heights on each node.
    //this way we'll be able to calculate max possible distances between
    //left and right at each node.
    private void maxDistBetweenTwoNodes(TreeNode root, int[] max) {
        if (root == null) return;

        int left = heightOfBTDFS(root.left);
        int right = heightOfBTDFS(root.right);
        max[0] = Math.max(max[0], left+right);
        maxDistBetweenTwoNodes(root.left, max);
        maxDistBetweenTwoNodes(root.right, max);
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
