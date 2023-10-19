package strvr.bst;

//@link - https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/description/
//@strvr - https://www.youtube.com/watch?v=cX_kPV_foZc&ab_channel=takeUforward
public class LowestCommonAncestorOfBST {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode currNode = root;
        while (currNode != null) {
            //go left if both nodes are in smaller
            if (p.val < currNode.val && q.val < currNode.val) currNode = currNode.left;
                //right if both nodes are larger.
            else if (p.val > currNode.val && q.val > currNode.val) currNode = currNode.right;

                //finally the moment both nodes are on other side of root, that node will be our ans.
            else return currNode;
        }

        return currNode;
    }
}
