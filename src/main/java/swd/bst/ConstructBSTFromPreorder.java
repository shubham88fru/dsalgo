package swd.bst;

//@link - https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/description/
public class ConstructBSTFromPreorder {
    public TreeNode bstFromPreorder(int[] preorder) {
        int[] currIndex = {0};

        //For root node, there's no boundation on what value it can have,
        //so for root node, we say that it can attain any value less than
        //infinity.
        return constructBSTFromPreorder(preorder, currIndex, Integer.MAX_VALUE);
    }

    private TreeNode constructBSTFromPreorder(int[] preorder, int[] currIndex, int maxPossibleValue) {
        if (currIndex[0] >= preorder.length) return null;

        //if curr value in preorder array is more than max possible
        //value for the current sub tree, return null. We'll try to
        //set this value in parent's other half.
        if (preorder[currIndex[0]] > maxPossibleValue) return null;

        TreeNode root = new TreeNode(preorder[currIndex[0]]);
        currIndex[0] += 1;

        //max value possible in curr nodes's left subtree is a value
        //smaller than curr nodes's value.
        root.left = constructBSTFromPreorder(preorder, currIndex, root.val);

        //max value possible in curr node's right subtree is anything smaller
        //than `maxPossibleValue`
        root.right = constructBSTFromPreorder(preorder, currIndex, maxPossibleValue);

        return root;
    }
}
