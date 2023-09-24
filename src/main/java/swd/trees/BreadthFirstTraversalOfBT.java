package swd.trees;

import strvr.binarytree.GfgNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

//@link - https://practice.geeksforgeeks.org/problems/level-order-traversal/1
public class BreadthFirstTraversalOfBT {
    static ArrayList<Integer> levelOrder(GfgNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        bfs(root, ans);
        return ans;
    }

    private static void bfs(GfgNode root, List<Integer> ans) {
        if (root == null) return;

        Deque<GfgNode> queue = new ArrayDeque<>();
        queue.addLast(root);

        while (!queue.isEmpty()) {
            GfgNode currNode = queue.removeFirst();

            ans.add(currNode.data);
            if (currNode.left != null) {
                queue.addLast(currNode.left);
            }

            if (currNode.right != null) {
                queue.addLast(currNode.right);
            }
        }
    }
}
