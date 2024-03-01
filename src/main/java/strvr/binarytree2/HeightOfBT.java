package strvr.binarytree2;

import java.util.ArrayDeque;
import java.util.Deque;

//@link - https://leetcode.com/problems/maximum-depth-of-binary-tree/description/
//@strvr - https://takeuforward.org/data-structure/maximum-depth-of-a-binary-tree/
public class HeightOfBT {
    public int maxDepth(TreeNode root) {
        //Max depth of BT is nothing but its height.

        //return heightOfBTDFS(root);
        return heightOfBTBFS(root);
    }

    //1) Recursive (DFS) algorithm to find height of BT.
    private int heightOfBTDFS(TreeNode root) {
        //Base case. If already on a leaf node, no height.
        if (root == null) return 0;

        //height of left sub tree from a node.
        int left = 1 + heightOfBTDFS(root.left);

        //height of right sub tree from a node.
        int right = 1 + heightOfBTDFS(root.right);

        //find the max.
        return Math.max(left, right);
    }

    //2) Level order traversal approach
    private int heightOfBTBFS(TreeNode root) {
        if (root == null) return 0;

        //Initialize queue for bfs.
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.addLast(root);

        int level = 0;

        while (!queue.isEmpty()) {
            //At any level, num of nodes in queue is equal
            //to num of nodes at the level.
            int nodesOnLevel = queue.size();

            //For each node at curr level, add their children
            //to the queue.
            while (nodesOnLevel > 0) {
                TreeNode currNode = queue.removeFirst();
                if (currNode.left != null) queue.addLast(currNode.left);
                if (currNode.right != null) queue.addLast(currNode.right);
                nodesOnLevel -= 1;
            }

            //increment level at each level.
            level += 1;
        }

        return level;
    }
}
