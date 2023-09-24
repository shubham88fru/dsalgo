package strvr.binarytree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@link - https://practice.geeksforgeeks.org/problems/right-view-of-binary-tree/1
//@strvr - https://takeuforward.org/data-structure/right-left-view-of-binary-tree/
public class RightViewOfBT {
    ArrayList<Integer> rightView(GfgNode node) {
        ArrayList<Integer> ans = new ArrayList<>();
        //traverseForRightView(node, ans, 0, new HashMap<Integer, Integer>());
        traverseForRightViewOptimal(node, ans, 0);
        return ans;
    }

    //1) Optimal approach. No extra space for external DS.
    private void traverseForRightViewOptimal(GfgNode node, ArrayList<Integer> ans, int level) {
        if (node == null) return;

        if (level == ans.size()) ans.add(node.data);
        traverseForRightViewOptimal(node.right, ans, level+1);
        traverseForRightViewOptimal(node.left, ans, level+1);
    }

    //2) Okay ish approach: takes extra hashmap space.
    private void traverseForRightView(GfgNode root, List<Integer> ans, int height, Map<Integer, Integer> heightMap) {
        if (root == null) return;

        //If no node see at current height till now,
        // then visible in left view.
        if (!heightMap.containsKey(height)) {
            heightMap.put(height, height);
            ans.add(root.data);
        }

        //Note: We're not strictly following the pre-order traversal
        //convention here. Since we are processing the right node before
        //the left node. We're doing this because we want to populate the
        //hashmap with the first occurrence on the right side.
        //Hence, if we traverse right side first, it will populate the
        //hashmap before the left node and hence we won't then add
        //the left node to the answers list. This way answers list will only
        //consist of first occurrences of right most child.
        traverseForRightView(root.right, ans, height + 1, heightMap);
        traverseForRightView(root.left, ans, height + 1, heightMap);
    }
}
