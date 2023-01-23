package swd.trees;

import java.util.ArrayList;
import java.util.List;

//@link - https://leetcode.com/problems/n-ary-tree-preorder-traversal/solutions/
public class PreorderTraversalOfNAT {
    public List<Integer> preorder(Node root) {
        List<Integer> ans = new ArrayList<>();
        preorderNAT(root, ans);
        return ans;
    }

    //Recursive algorithm for preorder traversal of N-ary tree.
    private void preorderNAT(Node root, List<Integer> ans) {
        //Base case.
        if (root == null) return;

        //visit root.
        ans.add(root.val);

        //visit all children from left to right.
        for (Node child: root.children) {
            preorderNAT(child, ans);
        }
    }
}
