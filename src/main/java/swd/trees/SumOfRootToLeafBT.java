package swd.trees;

import java.util.ArrayList;
import java.util.List;

//@link - https://leetcode.com/problems/sum-of-root-to-leaf-binary-numbers/description/
public class SumOfRootToLeafBT {
    public int sumRootToLeaf(TreeNode root) {
        return dfs(root, new ArrayList<Integer>());
    }

    //calculate sum during pre-order traversal.
    private int dfs(TreeNode root, List<Integer> lst) {
        if (root == null) return 0;
        lst.add(root.val);
        int sum = 0;
        sum = dfs(root.left, lst) + dfs(root.right, lst);

        //if leaf node, add the sum.
        //by converting binary to decimal.
        if (root.left == null && root.right == null) {
            sum += toDecimal(lst);
        }

        //remove node when back-tracking.
        //this is important because when need to ensure
        //that when we are at a leaf node and calculating the
        //sum from the `lst`, the `lst` has only the nodes from
        //root to curr leaf node.
        lst.remove(lst.size()-1);
        return sum;
    }

    //Algorithm to convert a binary list to decimal.
    private int toDecimal(List<Integer> nums) {
        int res = 0;
        int power = 0;
        for (int j=(nums.size()-1); j>=0; j--) {
            int multiplier = (int)Math.pow(2, power);
            res += nums.get(j) * multiplier;
            power += 1;
        }
        return res;
    }
}
