package swd.bst;

//@link - https://leetcode.com/problems/validate-binary-search-tree/description/

//4 ways to solve. 2nd video of bst.
public class ValidateBST {

    /*** My Soln - First, it is nasty. Second, it doesn't even work for all test cases. ***/
//    public boolean isValidBST(TreeNode root) {
//        if (root.left == null && root.right == null) return true;
//        return validBST(root, root.val, false);
//    }
//
//    private boolean validBST(TreeNode root, int rootval, boolean isleftChild) {
//        if (root == null) return true;
//
//        boolean left = validBST(root.left, rootval, true);
//        boolean right = validBST(root.right, rootval, false);
//
//        if(root.left==null && root.right==null) {
//            if (isleftChild && root.val < rootval) return true;
//            else if (!isleftChild && root.val > rootval) return true;
//            return false;
//        } else if (root.left==null) {
//            return (root.val < root.right.val) && (right);
//        } else if (root.right == null ) {
//            return (root.val > root.left.val ) && left;
//        } else {
//            return (root.val>root.left.val ) && (root.val<root.right.val ) && left && right;
//        }
//    }

    /*** SWD Soln - Doesn't work ***/
    public boolean isValidBST(TreeNode root) {
        TreeNode prev = new TreeNode(Integer.MIN_VALUE);
        return validBST(root, prev);
    }

    private boolean validBST(TreeNode root, TreeNode prev) {
        if (root == null) return true;

        boolean left = validBST(root.left, prev);
        //if (prev == null) prev = root;
        if (root.val <= prev.val) return false;
        else prev = root;
        boolean right = validBST(root.right, prev);

        //if (root.val < prevValue) return false;

        return (left && right);
    }
}

class Test1 {
    public static void main(String[] args) {
        ValidateBST validateBST = new ValidateBST();
        TreeNode left = new TreeNode(1, null, null);
        TreeNode node = new TreeNode(1, left, null);
        validateBST.isValidBST(node);
    }
}
