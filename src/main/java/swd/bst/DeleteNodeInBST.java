package swd.bst;

//@link - https://leetcode.com/problems/delete-node-in-a-bst/description/
public class DeleteNodeInBST {

    /*** My Soln - Doesn't work, Idk what I did.  ***/
//    public TreeNode deleteNode(TreeNode root, int key) {
//        deleteIfPresent(root, root, key);
//        return root;
//    }
//
//    private void deleteIfPresent(TreeNode origRoot, TreeNode root, int key) {
//        if (root == null) return;
//
//        if (root.val == key) {
//            delete(origRoot, root);
//            return;
//        }
//
//        if (key > root.val) deleteIfPresent(origRoot, root.right, key);
//        else deleteIfPresent(origRoot, root.left, key);
//    }
//
//    private void delete(TreeNode origRoot, TreeNode root) {
//        int child = 0;
//        if (root.left != null && root.right != null) child = 2;
//        else if (root.left != null || root.right != null) child = 1;
//
//        switch (child) {
//            case 0:
//                root = null;
//                return;
//            case 1:
//                if (root.left != null) {
//                    root = root.left;
//                    root.left = null;
//                } else {
//                    root = root.right;
//                    root.right = null;
//                }
//                return;
//            case 2:
//                TreeNode closestBiggerNode = serach(root.right);
//                TreeNode temp = root;
//                root.val = closestBiggerNode.val;
//                deleteIfPresent(origRoot, origRoot, closestBiggerNode.val);
//        }
//    }
//
//    private TreeNode serach(TreeNode root) {
//        if (root.left == null) return root;
//        return serach(root.left);
//    }

    /*** SWD Soln ***/
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;

        //if `key` in left subtree, modify the currnode's left.
        if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else if (root.val < key) { //if `key` in right subtree, modify the currnode's right.
            root.right = deleteNode(root.right, key);
        } else { //if node found, we have 3 cases.

            //CASE 1: target node is a left node.
            //In this case, we simply delete the target node by
            //returning null to parent node.
            if (root.left == null && root.right == null) {
                root = null;
                return root;
            }

            //CASE 2: target node has exactly one child (left or right)
            //In this case, we remove the target by returning its child (left or right)
            //to the parent. Hence parent's left or right child becomes left or right
            //child of the target.
            if (root.left != null && root.right == null) {
                root = root.left;
                return root;
            }
            if (root.right != null && root.left == null) {
                root = root.right;
                return root;
            }

            //CASE 3: target node has 2 children.
            //In this case, we replace the target with the just greater value (leftmost child of first right child.)
            //and then delete the just greater node.
            TreeNode justGreater = findJustGreaterInRightSubtree(root.right);
            int temp = root.val; //root.val is nothing but `key` at this point.
            root.val = justGreater.val;
            justGreater.val = temp;

            root.right = deleteNode(root.right, key);
            return root;
        }

        return root;
    }

    private TreeNode findJustGreaterInRightSubtree(TreeNode root) {
        if (root == null) return null;
        if (root.left == null) return root;
        return findJustGreaterInRightSubtree(root.left);
    }

}

class Test {
    public static void main(String[] args) {

        TreeNode leftleft = new TreeNode(2, null, null);
        TreeNode leftright = new TreeNode(4, null, null);
        TreeNode rightright = new TreeNode(7, null, null);
        TreeNode left = new TreeNode(3, leftleft, leftright);
        TreeNode right = new TreeNode(3, null, rightright);
        TreeNode root = new TreeNode(5, left, right);

        DeleteNodeInBST deleteNodeInBST = new DeleteNodeInBST();
        deleteNodeInBST.deleteNode(root, 3);
    }
}
