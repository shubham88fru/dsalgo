package swd.trees;


import java.util.ArrayList;
import java.util.List;

//@link - https://leetcode.com/problems/leaf-similar-trees/description/
public class LeafSimilarBTs {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> leaves1 = new ArrayList<>();
        List<Integer> leaves2 = new ArrayList<>();

        //leaves of tree1.
        dfsForLeaves(root1, leaves1);

        //leaves of tree2.
        dfsForLeaves(root2, leaves2);

        //Inbuilt java collections method.
        //equals method on lists checks if content of
        //list1 is exactly same (in order) as content of list2.
        return leaves1.equals(leaves2);
    }

    //Pre-order traverse to search of leaves.
    private void dfsForLeaves(TreeNode root, List<Integer> leaves) {
        if (root == null) return;

        //if a leaf, push to list.
        if (root.left == null && root.right == null) {
            leaves.add(root.val);
        }

        dfsForLeaves(root.left, leaves);
        dfsForLeaves(root.right, leaves);
    }
}
