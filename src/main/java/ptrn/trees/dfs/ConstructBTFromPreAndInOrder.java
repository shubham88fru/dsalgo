package ptrn.trees.dfs;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/
//@strvr - https://takeuforward.org/data-structure/construct-a-binary-tree-from-inorder-and-preorder-traversal/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5736235307630592
public class ConstructBTFromPreAndInOrder {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        //Map for looking up current preorder array's index
        //in inorder array.
        //Idea is to use preorder traversal to determine the root,
        //and inorder traversal to determine the left and right subtrees
        //of that node.
        Map<Integer, Integer> indexMap = populateIndexMap(inorder);

        //keep track of pre order array index.
        int[] preorderIndex = {0};

        // return treeFromPreAndIn(preorder, inorder, preorderIndex, 0, inorder.length-1, indexMap);
        return treeFromPreAndIn2(preorder, preorderIndex, 0, inorder.length-1, indexMap);
    }


    //1) SWD solution.
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
        TreeNode left = treeFromPreAndIn(preorder, inorder, preorderArrayCurrIndex, currNodeLeftStart, currNodeLeftEnd, indexMap);

        //right tree.
        TreeNode right = treeFromPreAndIn(preorder, inorder, preorderArrayCurrIndex, currNodeRightStart, currNodeRightEnd, indexMap);

        //TreeNode = currNode, left and right.
        return new TreeNode(currNode, left, right);
    }

    //2) My/Edctv soln
    private TreeNode treeFromPreAndIn2(int[] preorder, int[] currIdx,
                                       int start, int end, Map<Integer, Integer> indexMap) {
        if (currIdx[0] >= preorder.length) return null;
        if (start > end) return null;

        TreeNode node = new TreeNode(preorder[currIdx[0]]);
        currIdx[0] += 1;
        node.left = treeFromPreAndIn2(preorder, currIdx, start, indexMap.get(node.val)-1, indexMap);
        node.right = treeFromPreAndIn2(preorder, currIdx, indexMap.get(node.val)+1, end, indexMap);
        return node;

    }


    private Map<Integer, Integer> populateIndexMap(int[] inorder) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }
        return indexMap;
    }
}

