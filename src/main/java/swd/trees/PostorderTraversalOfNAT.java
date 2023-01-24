package swd.trees;

import java.util.ArrayList;
import java.util.List;

//@link - https://leetcode.com/problems/n-ary-tree-postorder-traversal/description/
public class PostorderTraversalOfNAT {
    public List<Integer> postorder(Node root) {
        List<Integer> ans = new ArrayList<>();
        traversePostOrder(root, ans);
        return ans;
    }

    //Recursive algorithm for post order traversal of n-ary tree.
    private void traversePostOrder(Node root, List<Integer> ans) {
        if (root == null) return;

        //visit all children.
        for (Node child: root.children) {
            traversePostOrder(child, ans);
        }

        //visit root.
        ans.add(root.val);
    }
}
