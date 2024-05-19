package lc_potd;

//@link - https://leetcode.com/problems/distribute-coins-in-binary-tree/
//@check - https://www.youtube.com/watch?v=RkVF5PdZJ1w&ab_channel=AryanMittal
public class DistributeCoinsInBinaryTree {

    /**
     * Did not entirely come up with soln, had to take help.
     * check linked video.
     *
     * Soln works and makes sense, but still don't understand
     * how does it guarantee that minimum number of coins are
     * moved.
     */
    public int distributeCoins(TreeNode root) {
        int[] moves = {0};
        dfs(root, moves);

        return moves[0];
    }


    private int dfs(TreeNode root, int[] moves) {
        //null nodes will neither give (positive) nor require (-ve) anything.
        if (root == null) return 0;

        //leaf node will give whatever it has after saving one for itself.
        if (root.left == null && root.right == null) return (root.val-1);

        //explore children.
        int left = dfs(root.left, moves);
        int right = dfs(root.right, moves);

        //regardless whether the child is giving or wanting
        //coins, moves will be added (positive).
        moves[0] += Math.abs(left) + Math.abs(right);

        //return the leftover after giving or taking to
        //children and saving one for self, to the parent.
        return (root.val+left+right-1);
    }
}
