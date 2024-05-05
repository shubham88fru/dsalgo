package ptrn.trees.dfs;


//@link - https://leetcode.com/problems/invert-binary-tree/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6549540292526080
public class InvertBT {

    /*** My Soln ***/
    public TreeNode invertTree(TreeNode root) {
        return invertBT(root);
    }

    private TreeNode invertBT(TreeNode root) {
        if (root == null) return null;

        //Post order traversal,
        //so the left and right subtrees
        //are inverted and then we swap curr root's
        //left and right.
        invertBT(root.left);
        invertBT(root.right);

        TreeNode left = root.left;
        root.left = root.right;
        root.right = left;

        return root;

    }

    /*** SWD Soln ***/
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) return null;
        TreeNode leftAns = invertTree2(root.left);
        TreeNode rightAns = invertTree2(root.right);

        root.left = rightAns;
        root.right = leftAns;

        return root;
    }
}

class Test {
    public static void main(String[] args) {
        InvertBT invertBT = new InvertBT();
        TreeNode left = new TreeNode(2);
        TreeNode root = new TreeNode(1, left, null);
        invertBT.invertTree(root);
        System.out.println(root);
    }
}
