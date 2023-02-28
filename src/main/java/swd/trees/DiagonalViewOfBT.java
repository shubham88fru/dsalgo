package swd.trees;

import java.util.*;


//@link - https://practice.geeksforgeeks.org/problems/diagonal-traversal-of-binary-tree/1
public class DiagonalViewOfBT {

    /*** Doesn't work for all cases ***/
    /*** DFS works, check last video of bt **/
    public ArrayList<Integer> diagonal(GfgNode root) {
        //add your code here.
        Map<Integer, ArrayList<Integer>> slopeMap = new HashMap<>();
        traverseForHorizontalViewBFS(root, slopeMap);
        ArrayList<Integer> ans = new ArrayList<>();
        for (Map.Entry<Integer, ArrayList<Integer>> entry: slopeMap.entrySet()) {
            ans.addAll(entry.getValue());
        }
        return ans;
    }
    private static void traverseForHorizontalViewBFS(GfgNode root, Map<Integer, ArrayList<Integer>> slopeMap) {
        if (root == null) return;

        Deque<GfgNodeSlopeWrapper> queue = new ArrayDeque<>();

        queue.addLast(new GfgNodeSlopeWrapper(root, 0));

        while (!queue.isEmpty()) {
            GfgNodeSlopeWrapper currPair = queue.removeFirst();
            GfgNode currNode = currPair._node;
            int slope = currPair._slope;

            if (!slopeMap.containsKey(slope)) {
                ArrayList<Integer> lst = new ArrayList<>();
                lst.add(currNode.data);
                slopeMap.put(slope, lst);
            } else {
                ArrayList<Integer> lst = slopeMap.get(slope);
                lst.add(currNode.data);
                slopeMap.put(slope, lst);
            }

            if (currNode.left != null)
                queue.addLast(new GfgNodeSlopeWrapper(currNode.left, slope + 1));

            if (currNode.right != null)
                queue.addLast(new GfgNodeSlopeWrapper(currNode.right, slope));
        }
    }

}

class GfgNodeSlopeWrapper {
    GfgNode _node;
    int _slope;

    public GfgNodeSlopeWrapper(GfgNode _node, int _slope) {
        this._node = _node;
        this._slope = _slope;
    }
}
