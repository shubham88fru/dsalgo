package ptrn.trees.bfs;

import java.util.ArrayDeque;
import java.util.Deque;

//@link - https://leetcode.com/problems/populating-next-right-pointers-in-each-node/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5299879560871936
public class PopulateNextRightPointerInEachNode {
    public LCNode connect(LCNode root) {
        //bfs(root);
        optimal(root);
        return root;
    }

    //1) Optimal soln (Edctv soln)
    //T: O(N), S: O(1)
    private void optimal(LCNode root) {
        if (root == null) return;
        LCNode leftMost = root;
        while (leftMost.left != null) {
            LCNode curr = leftMost;
            while (curr != null) {
                curr.left.next = curr.right;
                if (curr.next != null)
                    curr.right.next = curr.next.left;
                curr = curr.next;
            }
            leftMost = leftMost.left;
        }
    }

    //2) Better soln (not optimal) - Using standard BFS.
    private void bfs(LCNode root) {
        if (root == null) return;

        Deque<LCNode> queue = new ArrayDeque<>();
        queue.addLast(root);

        while (!queue.isEmpty()) {
            int nodesToProcessAtCurrLevel = queue.size();
            while (nodesToProcessAtCurrLevel > 0) {
                LCNode currNode = queue.removeFirst();
                LCNode nextNode = queue.peekFirst();

                //Point all nodes at current level to
                //the next node, except the last one.
                //Last node of every level is set to null.
                if (nodesToProcessAtCurrLevel != 1) {
                    currNode.next = nextNode;
                }

                if (currNode.left != null) queue.addLast(currNode.left);
                if (currNode.right != null) queue.addLast(currNode.right);

                nodesToProcessAtCurrLevel -= 1;
            }
        }
    }
}

class Test4 {
    public static void main(String[] args) {
        PopulateNextRightPointerInEachNode populateNextRightPointerInEachNode
                = new PopulateNextRightPointerInEachNode();


        LCNode four = new LCNode(4, null, null, null);
        LCNode five = new LCNode(5, null, null, null);
        LCNode six = new LCNode(6, null, null, null);
        LCNode seven = new LCNode(7, null, null, null);
        LCNode three = new LCNode(3, six, seven, null);
        LCNode two = new LCNode(2, four, five, null);
        LCNode one = new LCNode(1, two, three, null);

        System.out.println(populateNextRightPointerInEachNode.connect(one));
    }
}

class LCNode {
    public int val;
    public LCNode left;
    public LCNode right;
    public LCNode next;

    public LCNode() {}

    public LCNode(int _val) {
        val = _val;
    }

    public LCNode(int _val, LCNode _left, LCNode _right, LCNode _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}
