package swd.trees;

import java.util.HashMap;
import java.util.Map;

public class ConstructBTFromPreAndInOrder {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        //Map for looking up current preorder array's index
        //in inorder array.
        Map<Integer, Integer> indexMap = populateIndexMap(inorder);

        //keep track of pre order array index.
        int[] preorderIndex = {0};

        return treeFromPreAndIn(preorder, inorder, preorderIndex, 0, inorder.length-1, indexMap);
    }

    private TreeNode treeFromPreAndIn(int[] preorder, int[] inorder, int[] preorderArrayCurrIndex,
                                      int inorderArrayStartIndex, int inorderArrayEndIndex,
                                      Map<Integer, Integer> indexMap) {

        //base case.
        if (preorderArrayCurrIndex[0] >= preorder.length) return null;
        if (inorderArrayStartIndex > inorderArrayEndIndex) return null;

        //curr node (root) in preorder array.
        int currNode = preorder[preorderArrayCurrIndex[0]];

        //start index for currNode's left sub tree
        int currNodeLeftStart = inorderArrayStartIndex;

        //end index for currNode's left sub tree
        int currNodeLeftEnd = indexMap.get(currNode) - 1;

        //start index for currNode's right sub tree.
        int currNodeRightStart = indexMap.get(currNode) + 1;

        //end index for currNode's right sub tree.
        int currNodeRightEnd = inorderArrayEndIndex;

        //next element in preorder array.
        preorderArrayCurrIndex[0] += 1;

        //left tree.
        TreeNode left = treeFromPreAndIn(preorder, inorder, preorderArrayCurrIndex, inorderArrayStartIndex, currNodeLeftEnd, indexMap);

        //right tree.
        TreeNode right = treeFromPreAndIn(preorder, inorder, preorderArrayCurrIndex, currNodeRightStart, currNodeRightEnd, indexMap);

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
