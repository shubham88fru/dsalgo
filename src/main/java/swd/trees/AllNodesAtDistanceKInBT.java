package swd.trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@link - https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
public class AllNodesAtDistanceKInBT {

    /** My Soln - Doesn't work for this case - [0,2,1,null,null,3]**/
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> ans = new ArrayList<>();
        int[] distOfTargetFromRoot = {-1};
        searchForDist(root, 0, distOfTargetFromRoot, target);
        traverseForNodesAtDistanceK(root, target, 0, distOfTargetFromRoot[0], k, ans);
        return ans;
    }

    private void searchForDist(TreeNode root, int depth, int[] dist, TreeNode target) {
        if (root == null) return;
        if (root.val == target.val) dist[0] = depth;

        searchForDist(root.left, depth+1, dist, target);
        searchForDist(root.right, depth+1, dist, target);
    }

    private void traverseForNodesAtDistanceK(TreeNode root, TreeNode targetNode, int currDist, int targetsDistFromRoot, int requiredDist, List<Integer> ans) {
        if (root == null) return;

        if ((requiredDist == Math.abs(targetsDistFromRoot - currDist)) || (requiredDist == targetsDistFromRoot + currDist)) {
            if (root.val != targetNode.val) ans.add(root.val);
        }

        traverseForNodesAtDistanceK(root.left, targetNode, currDist+1, targetsDistFromRoot, requiredDist, ans);
        traverseForNodesAtDistanceK(root.right, targetNode, currDist+1, targetsDistFromRoot, requiredDist, ans);
    }

    /** SWD Soln **/
    public List<Integer> distanceK2(TreeNode root, TreeNode target, int k) {
        Map<TreeNode, TreeNode> parentsmap = new HashMap<>();

        //store a map of all nodes of tree v/s their parents;
        traverseForParents(root, null, parentsmap);

        List<Integer> ans = new ArrayList<>();

        //Start iterating from the targetNode (not the root)
        traverseForAllNodesAtDistK(target, k, ans, parentsmap, new HashMap<TreeNode, Integer>());

        return ans;
    }

    //Algorithm to find parents of all nodes.
    private void traverseForParents(TreeNode root, TreeNode parent, Map<TreeNode, TreeNode> parentsmap) {
        if (root == null) return;

        parentsmap.put(root, parent);
        traverseForParents(root.left, root, parentsmap);
        traverseForParents(root.right, root, parentsmap);
    }

    private void traverseForAllNodesAtDistK(TreeNode node, int k, List<Integer> ans, Map<TreeNode, TreeNode> parentsmap, Map<TreeNode, Integer> seenmap) {
        if (node == null) return;

        //If already visited, return.
        if (seenmap.containsKey(node)) return;

        //else, mark visited.
        seenmap.put(node, 1);

        if (k == 0) ans.add(node.val);

        //visit left
        traverseForAllNodesAtDistK(node.left, k-1, ans, parentsmap, seenmap);
        //visit right
        traverseForAllNodesAtDistK(node.right, k-1, ans, parentsmap, seenmap);
        //visit parent
        traverseForAllNodesAtDistK(parentsmap.get(node), k-1, ans, parentsmap, seenmap);
    }
}
