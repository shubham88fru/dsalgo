package swd.trees;

import java.util.ArrayList;
import java.util.List;

//@link - https://leetcode.com/problems/binary-tree-paths/description/
public class BTPaths {
    /*** My Soln - Work like a charm ***/
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ans = new ArrayList<>();
        dfs(root, new ArrayList<String>(), ans);
        return ans;
    }

    //Pre-order traverse to solve this problem.
    private void dfs(TreeNode root, List<String> nodes, List<String> lst) {
        if (root == null) return;

        nodes.add(Integer.toString(root.val));
        dfs(root.left, nodes, lst);
        dfs(root.right, nodes, lst);

        //if leaf node, join the nodes with `->`
        //and add to answers list.
        if (root.left == null && root.right == null) {
            String path = String.join("->", nodes);
            lst.add(path);
        }

        //remove node when back-tracking.
        //this is important because when need to ensure
        //that when we are at a leaf node and forming the
        //path, we only have the nodes which fall in path
        //consisting of root to current node.
        nodes.remove(nodes.size()-1);
    }

    /*** SWD Soln - Work like a charm ***/
    public List<String> binaryTreePaths2(TreeNode root) {
        List<String> ans = new ArrayList<>();
        rootToLeaf(root, "" + root.val, ans);
        return ans;
    }

    private void rootToLeaf(TreeNode root, String currentPath, List<String> answer) {
        if (root.left == null && root.right == null) {
            answer.add(currentPath);
            return;
        }

        if (root.left != null)
            rootToLeaf(root.left, currentPath+"->"+ root.left.val, answer);

        if (root.right != null)
            rootToLeaf(root.right, currentPath + "->"+root.right.val, answer);

    }
}
