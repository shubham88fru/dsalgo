package swd.trees;

import java.util.*;

//@link - https://leetcode.com/problems/average-of-levels-in-binary-tree/description/
public class AverageOfLevelsInBT {
    public List<Double> averageOfLevels(TreeNode root) {
        //run bfs.
        return bfsForLevelAverage(root);
    }

    //BFS algorithm to solve the problem.
    private List<Double> bfsForLevelAverage(TreeNode root) {
        if (root == null) return Collections.singletonList(0d);

        List<Double> ans = new ArrayList<>();

        //Initialize queue for bfs.
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.addLast(root);

        while (!queue.isEmpty()) {
            //nodes at curr level will be size of queue.
            int nodeAtCurrLevel = queue.size();

            //to keep track of pending nodes to be processed at curr level.
            int nodesToProcessAtCurrLevel = queue.size();

            //summ of all nodes at curr level.
            double levelSum = 0d;

            //find sum of all nodes at curr level.
            while (nodesToProcessAtCurrLevel > 0) {
                TreeNode currNode = queue.removeFirst();
                levelSum += (currNode.val);

                if (currNode.left != null) queue.addLast(currNode.left);
                if (currNode.right != null) queue.addLast(currNode.right);

                nodesToProcessAtCurrLevel -= 1;
            }

            //push average of curr level to list.
            ans.add(levelSum/nodeAtCurrLevel);
        }

        return ans;
    }
}
