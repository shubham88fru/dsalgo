package lc_potd;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

//@link - https://leetcode.com/problems/kth-largest-sum-in-a-binary-tree/
public class KthLargestSumInABinaryTree {
    public long kthLargestLevelSum(TreeNode root, int k) {
        return mysol(root, k);
    }

    private long mysol(TreeNode root, int k) {
        PriorityQueue<Long> minHeap = new PriorityQueue<>();

        Deque<TreeNode> q = new ArrayDeque<>();
        q.addLast(root);

        while (!q.isEmpty()) {
            int sz = q.size();
            long sum = 0;
            while (sz > 0) {
                TreeNode node = q.removeFirst();
                sum += node.val;
                if (node.left != null) q.addLast(node.left);
                if (node.right != null) q.addLast(node.right);
                sz -= 1;
            }

            if (minHeap.size() < k) {
                minHeap.add(sum);
            } else if (sum > minHeap.peek()) {
                minHeap.remove();
                minHeap.add(sum);
            }
        }

        return (minHeap.size() < k ? -1: minHeap.peek());
    }
}
