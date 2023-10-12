package swd.trees;


import java.util.ArrayDeque;
import java.util.Deque;

//@link - https://leetcode.com/problems/sum-of-left-leaves/description/
public class SumOfLeftLeavesInBT {
    /*** My Soln - Works but kinda nasty code. ***/
    public int sumOfLeftLeaves(TreeNode root) {
        return dfsForLeftLeaves(root, false, 0);
    }

    //1) DFS Algorithm.
    private int dfsForLeftLeaves(TreeNode root, boolean isLeftChild, int sum) {
        if (root == null) return sum;
        if (root.left == null && root.right == null && isLeftChild) {
            sum += root.val;
        }

        sum = dfsForLeftLeaves(root.left, true, sum);
        sum = dfsForLeftLeaves(root.right, false, sum);
        return sum;
    }

    /*** SWD Soln ***/
    public int sumOfLeftLeaves2(TreeNode root) {
        return dfsForLeftLeaves2(root, false);
    }

    //1) DFS Algorithm
    private int dfsForLeftLeaves2(TreeNode root, boolean isLeftChild) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) {
            return isLeftChild ? root.val : 0;
        }

        int leftAns = dfsForLeftLeaves2(root.left, true);
        int rightAns = dfsForLeftLeaves2(root.right, false);

        return leftAns + rightAns;
    }

    //2) BFS algorithm.
    private int bfsForLeftLeaves(TreeNode root) {
        if (root == null) return 0;

        Deque<EnhancedNode> queue = new ArrayDeque<>();
        queue.addLast(new EnhancedNode(root, false));

        int ans = 0;
        while (!queue.isEmpty()) {
            EnhancedNode ennode = queue.removeFirst();
            TreeNode currNode = ennode.node;
            //if leaf and left child, add to sum.
            if (currNode.left == null && currNode.right == null && ennode.isLeftChild) {
                ans += currNode.val;
            }

            if (currNode.left != null) queue.addLast(new EnhancedNode(currNode.left, true));
            if (currNode.right != null) queue.addLast(new EnhancedNode(currNode.right, false));
        }
        return ans;
    }
}

//Wraps the TreeNode with the extra info
//whether it is a left child or not.
class EnhancedNode {
    public TreeNode node;
    public boolean isLeftChild;

    public EnhancedNode(TreeNode node, boolean isLeftChild) {
        this.node = node;
        this.isLeftChild = isLeftChild;
    }
}
