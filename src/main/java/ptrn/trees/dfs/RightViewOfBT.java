package ptrn.trees.dfs;

import java.util.*;

//@link - https://leetcode.com/problems/binary-tree-right-side-view/description/
        //https://practice.geeksforgeeks.org/problems/right-view-of-binary-tree/1
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/4676245086011392
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

        //If no node seen at current height till now,
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

    //3) My soln using BFS
    private List<Integer> bfs(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;
        Deque<TreeNode> q = new ArrayDeque<>();
        q.addLast(root);
        while (!q.isEmpty()) {
            int els = q.size();
            while (els != 0) {
                TreeNode curr = q.removeFirst();
                if (curr.left != null) q.addLast(curr.left);
                if (curr.right != null) q.addLast(curr.right);
                //at each level, the last element (i.e. rightmost)
                //will be part of the right side view.
                els -= 1;
                if (els == 0) ans.add(curr.val);
            }
        }
        return ans;
    }
}

class GfgNode {
    int data;
    GfgNode left, right;

    GfgNode(int item) {
        data = item;
        left = right = null;
    }
}
