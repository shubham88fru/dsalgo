package swd.trees;

import java.util.ArrayList;
import java.util.List;

//@link - https://practice.geeksforgeeks.org/problems/boundary-traversal-of-binary-tree/1?utm_source=gfg&utm_medium=article&utm_campaign=bottom_sticky_on_article
public class BoundaryTraversalOfTree {
    ArrayList<Integer> boundary(BTPaths.GfgNode node) {
        ArrayList<Integer> ans = new ArrayList<>();

        //if root node is a leaf, it will get added
        //twice by addLeafNodes call, so prevent that.
        if (!isLeafNode(node))
            ans.add(node.data);

        //add all left boundary.
        addLeftBoundary(node.left, ans);

        //add all leaf node.
        addLeafNodes(node, ans);

        //add all right node.
        addRightBoundary(node.right, ans);

        return ans;
    }

    private void addLeftBoundary(BTPaths.GfgNode node, List<Integer> ans) {
        if (node == null) return;

        //add only if not a leaf node.
        if (!isLeafNode(node)) {
            ans.add(node.data);
        }

        if (node.left != null)
            addLeftBoundary(node.left, ans);
        else if (node.right != null)
            addLeftBoundary(node.right, ans);
    }

    private void addRightBoundary(BTPaths.GfgNode node, List<Integer> ans) {
        if (node == null) return;

        if (node.right != null)
            addRightBoundary(node.right, ans);

        else if (node.left != null)
            addRightBoundary(node.left, ans);

        //Traverse backwards because its right boundary
        //and add only if not a leaf node.
        if (!isLeafNode(node)) {
            ans.add(node.data);
        }

    }

    private void addLeafNodes(BTPaths.GfgNode node, List<Integer> ans) {
        if (node == null) return;

        if (isLeafNode(node)) {
            ans.add(node.data);
        }

        addLeafNodes(node.left, ans);
        addLeafNodes(node.right, ans);
    }

    private boolean isLeafNode(BTPaths.GfgNode node) {
        return node.left == null && node.right == null;
    }
}
