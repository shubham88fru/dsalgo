package ptrn.trees.dfs;

//@link - https://leetcode.com/problems/kth-smallest-element-in-a-bst/description/
//@strvr - https://takeuforward.org/data-structure/kth-largest-smallest-element-in-binary-search-tree/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5283102567694336
//TODO: modify the code to be similar to inorder successor.
public class KthSmallestElementInBST {

    //1. SWD soln.
    public int kthSmallest(TreeNode root, int k) {
        int[] karr = {k};
        return findKthSmallest(root, karr);
    }

    //exploiting a property of bst that
    //the inorder traversal of bst is sorted.
    private int findKthSmallest(TreeNode root, int[] karr) {
        if (root == null) return -1;

        //left
        int left = findKthSmallest(root.left, karr);

        //root
        //when processing a node, we know that its the next
        //element in a sorted manner, so we keep decrementing
        //our counter (which was initialized to k),
        //till it becomes 1.
        //Although, I'm not sure why we did this left == -1 and left != -1 check.
        //Might have to check swd video again.
        if (karr[0] == 1 && left == -1) {
            return root.val;
        } else karr[0] -= 1;

        if (left != -1) return left;

        //right
        int right = findKthSmallest(root.right, karr);
        return right;
    }

    //2. My soln (and edctv soln)
    //Using the fact that inorder traversal of a bst is sorted.
    public int kthSmallest2(TreeNode root, int k) {
        int[] smallest = {0};
        int[] kay = {k};
        dfs(root, kay, smallest);
        return smallest[0];
    }

    private void dfs(TreeNode root, int[] k, int[] smallest) {
        if (root == null) return ;

        dfs(root.left, k, smallest);
        k[0] -= 1;
        if (k[0] == 0) smallest[0] = root.val;
        dfs(root.right,k, smallest);
    }
}