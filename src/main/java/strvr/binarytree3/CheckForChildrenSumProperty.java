package strvr.binarytree3;

//@link - https://www.codingninjas.com/studio/problems/children-sum-property_8357239?leftPanelTab=1
//@strvr -https://takeuforward.org/data-structure/check-for-children-sum-property-in-a-binary-tree/
public class CheckForChildrenSumProperty {
    public static boolean isParentSum(Node root) {
        if (root == null) return true;
        // Write your code here.
        return childrenSum(root) != -1;
    }


    private static int childrenSum(Node root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return root.data;
        int left = childrenSum(root.left);
        if (left == -1) return -1;
        int right = childrenSum(root.right);
        if (right == -1) return -1;

        return root.data == (left+right) ? root.data : -1;

    }
}

class Node {
    int data;
    Node left, right;

    Node(int item) {
        data = item;
        left = right = null;
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
