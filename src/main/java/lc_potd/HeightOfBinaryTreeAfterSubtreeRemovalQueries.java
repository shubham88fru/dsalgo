package lc_potd;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/height-of-binary-tree-after-subtree-removal-queries/
//@check - https://www.youtube.com/watch?v=eEfW7CLbhvU&t=6s&ab_channel=codestorywithMIK
public class HeightOfBinaryTreeAfterSubtreeRemovalQueries {

    /*
         Coded by me but approach is completely inspired by mik.
     */
    public int[] treeQueries(TreeNode root, int[] queries) {
        Map<Integer, Integer> height = new HashMap<>(); //height of each node of tree.
        Map<Integer, Integer> level = new HashMap<>(); //depth of each node of tree.

        Map<Integer, Integer> fMaxAtLevel = new HashMap<>(); //max height at a level.
        Map<Integer, Integer> sMaxAtLevel = new HashMap<>();//second max height at a level.

        dfs(root, height, level, fMaxAtLevel, sMaxAtLevel, 0);

        int[] ans = new int[queries.length];
        for (int i=0; i<queries.length; i++) {
            int node = queries[i];
            int levelOfNode = level.get(node);
            int heightOfNode = height.get(node);

            int maxAtNodesLevel = fMaxAtLevel.get(levelOfNode);
            //if node to be deleted is the max height node of current level,
            //get from second max on the level.
            //(but what if there's no second max?) that's where it was necessary to
            //initialize the fMax and sMax maps with -1 (see submission history).
            if (heightOfNode == maxAtNodesLevel) {
                ans[i] = levelOfNode + sMaxAtLevel.get(levelOfNode) ;
            } else ans[i] = levelOfNode + maxAtNodesLevel; //else
        }

        return ans;
    }

    private int dfs(TreeNode root, Map<Integer, Integer> height,
                    Map<Integer, Integer> level, Map<Integer, Integer> fMaxAtLevel,
                    Map<Integer, Integer> sMaxAtLevel, int currLevel) {

        if (root == null) return 0;

        level.put(root.val, currLevel);

        int left = 1 + dfs(root.left, height, level, fMaxAtLevel, sMaxAtLevel, currLevel+1);
        int right = 1 + dfs(root.right, height, level, fMaxAtLevel, sMaxAtLevel,currLevel+1);

        int mH = Math.max(left, right)-1;

        height.put(root.val, mH); //height of each node.

        /*
            This initialization with -1 was kinda crucial to this approach.
            I had initialized with 0, but that fails an edge case (see submission history)
            Initializing with -1 kind off luckily works.
        */
        if (!fMaxAtLevel.containsKey(currLevel)) fMaxAtLevel.put(currLevel, -1);
        if (!sMaxAtLevel.containsKey(currLevel)) sMaxAtLevel.put(currLevel, -1);

        //max and second max.
        if (mH >= fMaxAtLevel.get(currLevel)) {
            int curr = fMaxAtLevel.get(currLevel);
            fMaxAtLevel.put(currLevel, mH);
            sMaxAtLevel.put(currLevel, curr);
        } else if (mH >= sMaxAtLevel.get(currLevel)) {
            sMaxAtLevel.put(currLevel, mH);
        }

        return Math.max(left, right);
    }
}
