package swd.trees;

import strvr.binarytree.GfgNode;

import java.util.*;


//@link - https://practice.geeksforgeeks.org/problems/diagonal-traversal-of-binary-tree/1
public class DiagonalViewOfBT {

    /*** Soln 1 - Doesn't work for all cases ***/
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

    /*** Soln 2 - Using queue, but not exactly a bfs a approach ***/
    public ArrayList<Integer> diagonalViewUsingQueue(GfgNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        Deque<GfgNode> queue = new ArrayDeque<>();

        GfgNode currNode = root;
        while (currNode != null) {
            ans.add(currNode.data);

            //keep queueing left nodes
            if (currNode.left != null) {
                queue.addLast(currNode.left);
            }

            //keep moving right (while queueing left leaves as above)
            if (currNode.right != null) {
                currNode = currNode.right;
            } else if (queue.isEmpty()) {
                //queue empty means we're done.
                //All nodes processed.
                currNode = null;
            } else {
                //if queue has elements, but curr node
                //has no right child, pick the element
                //from queue (a left child) and repeat.

                //Basically, in a digonal view, we'll process
                //all the right childs at one stage. Once all
                //right childs are done, we'll have to move to next
                // diagonal level. For that, pick the firstmost
                //left child (because of queue behavior) and repeat the
                //process of moving right..right..right.. and queueing
                //left..left..left again.
                currNode = queue.removeFirst();
            }
        }
        return ans;
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
