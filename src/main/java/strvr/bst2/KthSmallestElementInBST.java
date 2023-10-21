package strvr.bst2;

//@link - https://leetcode.com/problems/kth-smallest-element-in-a-bst/description/
//@strvr - https://takeuforward.org/data-structure/kth-largest-smallest-element-in-binary-search-tree/
//TODO: modify the code to be similar to inorder successor.
public class KthSmallestElementInBST {
    public int kthSmallest(TreeNode root, int k) {
        int[] karr = {k};
        return findKthSmallest(root, karr);
    }

    //exploiting a property of bst that
    //the inorder traversal of bst is sorted.
    private int findKthSmallest(TreeNode root, int[] karr) {
        if (root == null) return -1;

        //left
        int left = findKthSmallest(root.left, karr);

        //root
        //when processing a node, we know that its the next
        //element in a sorted manner, so we keep decrementing
        //our counter (which was initialized to k),
        //till it becomes 1.
        //Although, I'm not sure why we did this left == -1 and left != -1 check.
        //Might have to check swd video again.
        if (karr[0] == 1 && left == -1) {
            return root.val;
        } else karr[0] -= 1;

        if (left != -1) return left;

        //right
        int right = findKthSmallest(root.right, karr);
        return right;
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