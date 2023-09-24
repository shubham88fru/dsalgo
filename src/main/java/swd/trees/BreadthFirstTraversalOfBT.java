package swd.trees;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

//@link - https://practice.geeksforgeeks.org/problems/level-order-traversal/1
public class BreadthFirstTraversalOfBT {
    static ArrayList<Integer> levelOrder(BTPaths.GfgNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        bfs(root, ans);
        return ans;
    }

    private static void bfs(BTPaths.GfgNode root, List<Integer> ans) {
        if (root == null) return;

        Deque<BTPaths.GfgNode> queue = new ArrayDeque<>();
        queue.addLast(root);

        while (!queue.isEmpty()) {
            BTPaths.GfgNode currNode = queue.removeFirst();

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
