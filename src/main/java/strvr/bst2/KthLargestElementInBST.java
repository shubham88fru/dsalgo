package strvr.bst2;

//@link - https://practice.geeksforgeeks.org/problems/kth-largest-element-in-bst/1
//@strvr - https://takeuforward.org/data-structure/kth-largest-smallest-element-in-binary-search-tree/
public class KthLargestElementInBST {
    public int kthLargest(GfgNode root,int K){
        int[] karr = {K};
        return findKthLargest(root, karr);
    }

    //doing reverse inorder. will give the elements in sorted manner
    //from high to low.
    private int findKthLargest(GfgNode root, int[] karr) {
        if (root == null) return -1;

        int right = findKthLargest(root.right, karr);
        if (karr[0] == 1 && right == -1) {
            return root.data;
        } else karr[0] -= 1;

        if (right != -1) return right;

        int left = findKthLargest(root.left, karr);
        return left;
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
