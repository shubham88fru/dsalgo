package swd.trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

//@link - https://practice.geeksforgeeks.org/problems/top-view-of-binary-tree/1
public class TopViewOfBT {

    /** My and SWD's Soln - Doesn't work for all cases. **/
    static ArrayList<Integer> topView(GfgNode root) {
        ArrayList<Integer> ans1 = new ArrayList<>();
        ArrayList<Integer> ans2 = new ArrayList<>();
        Map<Integer, Integer> mp = new LinkedHashMap<Integer, Integer>();

        traverseForTopViewLeft(root, 0, mp);
        for (Map.Entry<Integer, Integer> entry : mp.entrySet()) {
            if (entry.getKey() <= 0)
                ans1.add(entry.getValue());
            else
                ans2.add(entry.getValue());
        }
        Collections.reverse(ans1);
        ans1.addAll(ans2);
        return ans1;
    }

    private static void traverseForTopViewLeft(GfgNode root, int index, Map<Integer, Integer> breadthMap) {
        if (root == null) return;

        if (!breadthMap.containsKey(index)) {
            breadthMap.put(index, root.data);
        }

        traverseForTopViewLeft(root.left, index - 1, breadthMap);
        traverseForTopViewLeft(root.right, index + 1, breadthMap);
    }
}
