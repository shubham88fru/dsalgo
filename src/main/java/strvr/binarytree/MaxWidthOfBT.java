package strvr.binarytree;

import java.util.ArrayDeque;
import java.util.Deque;

//@link - https://leetcode.com/problems/maximum-width-of-binary-tree/
//@strvr - https://takeuforward.org/data-structure/maximum-width-of-a-binary-tree/
public class MaxWidthOfBT {
    public int widthOfBinaryTree(TreeNode root) {
        return bfsForMaxWdith(root);
    }

    //Idea is to index all nodes from 1 to N.
    //and then question becomes as easy as finding the
    //maxIdx-minIdx+1 for each level and comparind which gives the max.
    //NOTE: Since we are multiplying, 2*i etc. if i is large, it could
    //lead to some overflow issues. Checkout strvr for a workaround of this.
    private int bfsForMaxWdith(TreeNode root) {
        if (root == null) return 0;

        int maxWidth = 1;
        Deque<NodeWrapper2> q = new ArrayDeque<>();
        q.addLast(new NodeWrapper2(root, 1));

        while (!q.isEmpty()) {
            int size = q.size();

            while (size > 0) {
                NodeWrapper2 nw = q.removeFirst();

                if (nw.node.left != null) {
                    //for a parent with index i, its left child will be at
                    //index 2*i (if we are following 1 based indexing at root. Else if 0 based then 2*i+1)
                    int leftChildIdx = 2*nw.idx;
                    q.addLast(new NodeWrapper2(nw.node.left, leftChildIdx));
                }

                if (nw.node.right != null) {
                    //for a parent with index i, its right child will be at
                    //index 2*i+1 (if we are following 1 based indexing at root. Else if 0 based then 2*i+2)
                    int rightChildIdx = 2*nw.idx+1;
                    q.addLast(new NodeWrapper2(nw.node.right, rightChildIdx));
                }

                size -= 1;
            }

            //After each level, compare the width with the current max.
            if (!q.isEmpty() && (q.peekLast().idx - q.peekFirst().idx + 1) > maxWidth) {
                maxWidth = q.peekLast().idx - q.peekFirst().idx + 1;
            }
        }

        return maxWidth;
    }
}

class NodeWrapper2 {
    TreeNode node;
    int idx;

    public NodeWrapper2(TreeNode node, int idx) {
        this.node = node;
        this.idx = idx;
    }

    public String toString() {
        return "[" + node.val + ", " + idx + "]";
    }
}
