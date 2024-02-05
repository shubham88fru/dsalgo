package ptrn.backtracking;

//@link - https://leetcode.com/problems/house-robber-iii/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5389457395286016
public class HouseRobberIII {
    public int rob(TreeNode root) {
        int[] ans = dfs(root);
        return Math.max(ans[0], ans[1]);
    }

    /**
     Returns an array of profit earned at a node when
     the node is included and when its not included.
     i.e. [ includeRoot, excludeRoot ]
     */
    private int[] dfs(TreeNode root) {
        //no earnings - base case.
        if (root == null) return new int[] {0, 0};

        //recursively traverse the left subtree.
        int[] left = dfs(root.left);

        //recursively traverse the right subtree.
        int[] right = dfs(root.right);

        //At this point, we are at a node and we have traversed
        //its left and right. So now we are in a position to make some
        //decisions. If we decide to include the current node, then for sure,
        //we can't include it's left and right child. So, `includeRoot` calculates
        //the profit that we'll make, if we rob the current node.
        //On the other hand, if we decide to not rob the current node, then we have two
        //options - rob the child or don't rob the child (Note that not robbing the current
        //node doesn't enforce that we must rob the child). And so, for the case when we
        //don't rob the current node, we'll choose the max from childs that we'll get when
        //we rob them or when we don't rob them.
        //With the `includeRoot` and `excludeRoot` caculated for the current node, we can return
        //to the parent node which will now have the information as to what profit it will make
        //if it decides to choose or not choose this child.


        //curr root, profit from left child w/o root, profit from right child w/o root.
        int includeRoot = root.val + left[1] + right[1];

        //exclude curr root. Max profit from left and Right, considering both cases of
        //including their root or excluding their root.
        int excludeRoot = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        //For each node, return both.
        return new int[] { includeRoot, excludeRoot };
    }
}

/**
 * Definition for a binary tree node.
 **/
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
