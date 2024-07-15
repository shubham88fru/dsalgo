package lc_potd;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//@link - https://leetcode.com/problems/create-binary-tree-from-descriptions/description/
public class CreateBinaryTreeFromDescription {

    //My soln.
    public TreeNode createBinaryTree(int[][] descriptions) {
        Set<Integer> children = new HashSet<>();
        for (int[] desc: descriptions) {
            children.add(desc[1]);
        }

        int root = -1;
        for (int[] desc: descriptions) {
            if (!children.contains(desc[0])) {
                root = desc[0];
                break;
            }
        }

        Map<Integer, TreeNode> tree = new HashMap<>();
        for (int[] desc: descriptions) {
            int rt = desc[0];
            int child = desc[1];
            TreeNode rtn = null;
            TreeNode childn = null;
            if (!tree.containsKey(rt)) {
                tree.put(rt, new TreeNode(rt));
            }
            rtn = tree.get(rt);

            if (!tree.containsKey(child)) {
                tree.put(child, new TreeNode(child));
            }
            childn = tree.get(child);


            if (desc[2] == 1) rtn.left = childn;
            else rtn.right = childn;

        }

        return tree.get(root);
    }
}
