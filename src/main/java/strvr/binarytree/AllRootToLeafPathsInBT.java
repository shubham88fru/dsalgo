package strvr.binarytree;

import java.util.ArrayList;
import java.util.List;

//@link - https://www.codingninjas.com/studio/problems/all-root-to-leaf-paths-in-binary-tree._983599
public class AllRootToLeafPathsInBT {
    public static List<String> allRootToLeaf(BinaryTreeNode root) {
        // Write your code here.
        List<String> ans = new ArrayList<>();
        traversePreorder(root, "", ans);
        return new ArrayList<>(ans);
    }

    private static void traversePreorder(BinaryTreeNode root, String path, List<String> ans) {
        if (root == null) return;

        if (root.left == null && root.right == null) {
            path += " " + root.data;
            ans.add(path.trim());
            return;
        }

        path += " " + root.data;
        traversePreorder(root.left, path, ans);
        traversePreorder(root.right, path, ans);

        path.substring(0, path.length());

    }
}

class BinaryTreeNode {
    int data;
    BinaryTreeNode left;
    BinaryTreeNode right;

    BinaryTreeNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
};