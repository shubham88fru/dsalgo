package lc_potd;

//@link - https://leetcode.com/problems/binary-search-tree-to-greater-sum-tree/description/
public class BinarySearchTreeToGreaterSumTree {
    public TreeNode bstToGst(TreeNode root) {
        int[] prevSum = {0};
        dfs(root, prevSum);
        return root;
    }


    /**
     In order traversal of a BST gives a sorted list.
     So, to get all elements bigger than a node in BST,
     we just need to look at the elements that come after
     the node in inorder traversal. Or, we could just
     follow a reverse inorder traversal and keep summing
     the elements coz that way when we are at any give node,
     the current sum will the sum of all elements larger than
     current node.
     */
    //My soln.
    private void dfs(TreeNode root, int[] prevSum) {
        if (root == null) return;

        //Follow a reverse inorder traversal
        //and keep updating the sum.
        dfs(root.right, prevSum); //right

        root.val += prevSum[0]; //node
        prevSum[0] = root.val;

        dfs(root.left, prevSum); //left
    }
}
