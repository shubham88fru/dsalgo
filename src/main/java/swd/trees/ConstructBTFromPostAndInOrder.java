package swd.trees;


import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/description/
public class ConstructBTFromPostAndInOrder {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        //Map for looking up current postorder array's index
        //in inorder array.
        Map<Integer, Integer> indexMap = populateIndexMap(inorder);

        //keep track of postorder array index.
        //start from right end.
        int[] postorderIndex = {postorder.length-1};

        return treeFromPostAndIn(postorder, inorder, postorderIndex, 0, inorder.length-1, indexMap);
    }

    private TreeNode treeFromPostAndIn(int[] postorder, int[] inorder, int[] postorderArrayCurrIndex,
                                       int inorderArrayStartIndex, int inorderArrayEndIndex,
                                       Map<Integer, Integer> indexMap) {

        //base case.
        if (postorderArrayCurrIndex[0] < 0) return null;
        if (inorderArrayStartIndex > inorderArrayEndIndex) return null;

        //curr node (root) in postorder array.
        int currNode = postorder[postorderArrayCurrIndex[0]];

        //start index for currNode's left sub tree
        int currNodeLeftStart = inorderArrayStartIndex;

        //end index for currNode's left sub tree
        int currNodeLeftEnd = indexMap.get(currNode) - 1;

        //start index for currNode's right sub tree.
        int currNodeRightStart = indexMap.get(currNode) + 1;

        //end index for currNode's right sub tree.
        int currNodeRightEnd = inorderArrayEndIndex;

        //next element in postorder array.
        postorderArrayCurrIndex[0] -= 1;

        //Postorder traversal is -> Left, Right, Root
        //But since we are iterating the postorder array from right,
        //we'll get root first, then the right child and then left.
        //Hence on decrementing the postorderArrayCurrIndedx, we'll first
        //get the right sub tree.

        //right tree.
        TreeNode right = treeFromPostAndIn(postorder, inorder, postorderArrayCurrIndex, currNodeRightStart, currNodeRightEnd, indexMap);

        //left tree.
        TreeNode left = treeFromPostAndIn(postorder, inorder, postorderArrayCurrIndex, inorderArrayStartIndex, currNodeLeftEnd, indexMap);

        //TreeNode = currNode, left and right.
        return new TreeNode(currNode, left, right);
    }


    private Map<Integer, Integer> populateIndexMap(int[] inorder) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }
        return indexMap;
    }
}
