package ptrn.trees.dfs;

//@link - https://leetcode.com/problems/subtree-of-another-tree/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6440302144651264
public class SubtreeOfAnotherTree {

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        return checkSubtree(root, subRoot);

//        boolean[] ans = new boolean[1];
//        dfs2(ans, root, subRoot);
//        return ans[0];
    }

    //my sol 1
    private boolean checkSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null) return false;
        if (root.val == subRoot.val) if(dfs(root, subRoot)) return true;

        return (checkSubtree(root.left, subRoot) || checkSubtree(root.right, subRoot));
    }

    private boolean dfs(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot != null) return false;
        if (root != null && subRoot == null) return false;
        if (root == null && subRoot == null) return true;

        if (root.val != subRoot.val) return false;
        boolean left = dfs(root.left, subRoot.left);
        boolean right = dfs(root.right, subRoot.right);

        return (left && right);
    }

    //my sol 2
    //dfs traverse the larger tree.
    private void dfs2(boolean[] ans, TreeNode root, TreeNode subRoot) {
        if (root == null) return;

        //for each sub subtree of the larger tree
        //check if the subtree is same as the smaller tree.
        if (isSame(root, subRoot)) {
            ans[0] = true;
            return;
        }

        if (!ans[0]) dfs2(ans, root.left, subRoot);
        if (!ans[0]) dfs2(ans, root.right, subRoot);
    }

    //standard two tree comparison algorithm.
    private boolean isSame(TreeNode t1, TreeNode t2) {
        if (t1 == null) if (t2 != null) return false;
        if (t2 == null) if (t1 != null) return false;

        if (t1 == null && t2 == null) return true;

        if (t1.val != t2.val) return false;

        boolean left = isSame(t1.left, t2.left);
        boolean right = isSame(t1.right, t2.right);

        return (left && right);
    }
}
