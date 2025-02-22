package lc_potd;

public class RecoverATreeFromPreorderTraversal {
    public TreeNode recoverFromPreorder(String traversal) {
        return mikssol(traversal);
    }

    private TreeNode mikssol(String traversal) {
        int[] idx = {0}; //painpoint.
        int[] dashes = {0};//painpoint.
        return dfs(traversal, 0, dashes, idx);
    }

    private TreeNode dfs(String traversal, int depth, int[] dashes, int[] idx) {
        if (idx[0] >= traversal.length()) return null;
        if (depth != dashes[0]) return null;

        StringBuffer sb = new StringBuffer();
        while (idx[0] < traversal.length() && Character.isDigit(traversal.charAt(idx[0]))) {
            sb.append(traversal.charAt(idx[0]));
            idx[0] += 1;
        }

        TreeNode newNode = new TreeNode(Integer.parseInt(sb.toString()));

        int j = idx[0];
        while (j < traversal.length() && traversal.charAt(j) == '-') {
            j += 1;
        }

        dashes[0] = j - idx[0];
        idx[0] = j;
        newNode.left = dfs(traversal, depth+1, dashes, idx);
        newNode.right = dfs(traversal, depth+1, dashes, idx);

        return newNode;
    }
}
