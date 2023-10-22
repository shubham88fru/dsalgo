package strvr.bst2;

//@link - https://practice.geeksforgeeks.org/problems/largest-bst/1
//@strvr - https://www.youtube.com/watch?v=X0oXMdtUDwo&ab_channel=takeUforward
public class LargestBSTInATree {
    // Return the size of the largest sub-tree which is also a BST
    int largestBst(GfgNode root) {
        // Write your code here
        return getSizeOfLargestBST(root).bstSize;
    }

    private GfgNodeData getSizeOfLargestBST(GfgNode root) {
        //a null node is a bst with zero size.
        if (root == null) return new GfgNodeData(0, Integer.MAX_VALUE, Integer.MIN_VALUE, true);

        //leaf nodes are bst with size 1. min and max values seen are the value of the node itself.
        if (root.left == null && root.right == null) return new GfgNodeData(1, root.data, root.data, true);

        //follow post order, so we can check left and right subtree first.
        //this will help us to determine if a given tree is a bst because we'll
        //already have data for its left and right subtrees.
        GfgNodeData leftAns = getSizeOfLargestBST(root.left);
        GfgNodeData rightAns = getSizeOfLargestBST(root.right);

        //size of bst seen till now in left subtree of curr node.
        int leftBSTSize = leftAns.bstSize;

        //size of bst seen till now in right subtree of curr node.
        int rightBSTSize = rightAns.bstSize;

        //min value seen in left subtree till now.
        int leftMin = leftAns.min;

        //min value seen in right subtree till now.
        int rightMin = rightAns.min;

        //min value till now including left subtree, right subtree, and curr node.
        int finalMin = Math.min(Math.min(leftMin, rightMin), root.data);

        //max value seen in left subtree till now.
        int leftMax = leftAns.max;

        //max value seen in right subtree till now.
        int rightMax = rightAns.max;

        //max value till now including left subtree, right subtree, and curr node.
        int finalMax = Math.max(Math.max(leftMax, rightMax), root.data);

        //for a node, if its left and right subtrees are bst
        //and the node's value is less than min in the right subtree
        //and more than the max in the left subtree, then current tree (curr node, its left and right)
        //is also a bst.
        if (leftAns.isBST
                && rightAns.isBST
                && root.data > leftAns.max
                && root.data < rightAns.min
        ) {

            //when bst, size of bst will be left size, right size, plus curr node.
            int finalSize = 1 + leftBSTSize + rightBSTSize;
            return new GfgNodeData(finalSize, finalMin, finalMax, true);
        } else {
            //when not a bst, size will be whatever the max we've seen either in left or right.
            int finalSize = Math.max(leftBSTSize, rightBSTSize);
            return new GfgNodeData(finalSize, finalMin, finalMax, false);
        }

    }
}

class Test4 {
    public static void main(String[] args) {
        GfgNode leftleft = new GfgNode(2, null, null);
        GfgNode left = new GfgNode(3, leftleft, null);
        GfgNode root = new GfgNode(1, left, null);

        LargestBSTInATree largestBSTInATree = new LargestBSTInATree();
        System.out.println(largestBSTInATree.largestBst(root));
    }
}

class GfgNodeData {
    int bstSize;
    int min;
    int max;
    boolean isBST;

    public GfgNodeData(int bstSize, int min, int max, boolean isBST) {
        this.bstSize = bstSize;
        this.min = min;
        this.max = max;
        this.isBST = isBST;
    }
}