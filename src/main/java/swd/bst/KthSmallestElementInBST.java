package swd.bst;

//@link - https://leetcode.com/problems/kth-smallest-element-in-a-bst/description/
//TODO: modify the code to be similar to inorder successor.
public class KthSmallestElementInBST {
    public int kthSmallest(TreeNode root, int k) {
        int[] karr = {k};
        return findKthSmallest(root, karr);
    }

    private int findKthSmallest(TreeNode root, int[] karr) {
        if (root == null) return -1;

        int left = findKthSmallest(root.left, karr);
        if (karr[0] == 1 && left == -1) {
            return root.val;
        } else karr[0] -= 1;

        if (left != -1) return left;

        int right = findKthSmallest(root.right, karr);
        return right;
    }
}
