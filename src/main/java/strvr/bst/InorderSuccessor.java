package strvr.bst;

//@link - https://practice.geeksforgeeks.org/problems/inorder-successor-in-bst/1?utm_source=gfg&utm_medium=article&utm_campaign=bottom_sticky_on_article
//@strvr - @nolink
public class InorderSuccessor {
    public GfgNode inorderSuccessor(GfgNode root, GfgNode x) {
        boolean[] found = {false};
        return inorder(root, x, found);
    }

    private GfgNode inorder(GfgNode root, GfgNode x, boolean[] found) {
        if (root == null) return null;

        GfgNode leftAns = inorder(root.left, x, found);
        if (leftAns != null) return leftAns;

        if (found[0]) {
            return root;
        }

        if (root.data == x.data) {
            found[0] = true;
        }

        GfgNode rightAns = inorder(root.right, x, found);
        return rightAns;
    }
}


class GfgNode {
    int data;
    GfgNode left, right;

    GfgNode(int item) {
        data = item;
        left = right = null;
    }

    GfgNode(int item, GfgNode left, GfgNode right) {
        data = item;
        this.left = left;
        this.right = right;
    }
}