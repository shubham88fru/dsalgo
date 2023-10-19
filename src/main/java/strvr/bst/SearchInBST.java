package strvr.bst;

//@link - https://leetcode.com/problems/search-in-a-binary-search-tree/description/
//@strvr - https://www.youtube.com/watch?v=KcNt6v_56cc&ab_channel=takeUforward
public class SearchInBST {
    public TreeNode searchBST(TreeNode root, int val) {
        //return searchBinaryDFS(root, val);
        return searchBinaryIterative(root, val);
    }

    //1) DFS (Recursive) approach.
    private TreeNode searchBinaryDFS(TreeNode root, int val) {
        if (root == null) return null;

        //if found return.
        if (root.val == val) return root;

        //if value less than root, then should be present (if at all)
        //in left subtree.
        //else should be present in right subtree.
        if (val < root.val) return searchBinaryDFS(root.left, val);
        else return searchBinaryDFS(root.right, val);
    }

    //2) Iterative approach.
    private TreeNode searchBinaryIterative(TreeNode root, int val) {
        TreeNode currNode = root;
        while (currNode != null) {
            if (val == currNode.val) return currNode;
            if (val < currNode.val) currNode = currNode.left;
            else currNode = currNode.right;
        }

        return null;
    }
}


