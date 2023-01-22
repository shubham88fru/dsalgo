package swd.trees;

import java.util.List;

//@link - https://leetcode.com/problems/maximum-depth-of-n-ary-tree/description/
public class HeightOfNAT {
    public int maxDepth(Node root) {
        return heightOfNAT(root);
    }

    private int heightOfNAT(Node root) {
        if (root == null) return 0;

        //initialize with 1 (not 0) coz, if a not is not null
        //and still has no childredn (ie. a leaf node), its contribution
        //to height of tree will be 1.
        int max = 1;

        //Iterate over all child nodes and cacluate height for each.
        //take max in each iteration.
        for (Node child: root.children) {
            int height = 1 + heightOfNAT(child);
            max = Math.max(max, height);
        }

        return max;
    }
}

// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};