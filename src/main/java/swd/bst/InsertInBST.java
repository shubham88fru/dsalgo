package swd.bst;


//@link - https://leetcode.com/problems/insert-into-a-binary-search-tree/description/
public class InsertInBST {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        //return insertInBSTIterative(root, val);
        return insertInBSTDFS(root, val);
    }

    //1) Iterative approach.
    private TreeNode insertInBSTIterative(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        TreeNode currNode = root;

        while (currNode != null) {
            //if val is less that curr node,
            //set left child to val (if leaf node) or move left.
            if (val < currNode.val) {
                if (currNode.left == null) {
                    currNode.left = new TreeNode(val);
                    return root;
                }
                currNode = currNode.left;
            } else {
                //if val is larger that curr node,
                //set right child to val (if leaf node) or move right.
                if (currNode.right == null) {
                    currNode.right = new TreeNode(val);
                    return root;
                }
                currNode = currNode.right;
            }
        }
        return root;
    }

    //2) DFS (Recursive) approach.
    private TreeNode insertInBSTDFS(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);

        if (val < root.val) root.left = insertInBSTDFS(root.left, val);
        else root.right = insertInBSTDFS(root.right, val);

        return root;
    }
}
