package swd.trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@link - https://practice.geeksforgeeks.org/problems/left-view-of-binary-tree/1
public class LeftViewOfBT {

    /** My Soln - Works well, but uses hashmap extra space. **/
    ArrayList<Integer> leftView(GfgNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        traverseForLeftView(root, ans, 0, new HashMap<Integer, Integer>());
        return ans;
    }

    //1) DFS approach.
    private void traverseForLeftView(GfgNode root, List<Integer> ans, int height, Map<Integer, Integer> heightMap) {
        if (root == null) return;

        //If no node see at current height till now,
        // then visible in left view.
        if (!heightMap.containsKey(height)) {
            heightMap.put(height, height);
            ans.add(root.data);
        }

        if (root.left != null) {
            traverseForLeftView(root.left, ans, height+1, heightMap);
        }

        if (root.right != null) {
            traverseForLeftView(root.right, ans, height+1, heightMap);
        }
    }
}

class GfgNode
{
    int data;
    GfgNode left, right;

    GfgNode(int item)
    {
        data = item;
        left = right = null;
    }
}