package ptrn.trees.dfs;

//@link - https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/4958582931718144
public class ConvertSortedArrayToBST {
    public TreeNode sortedArrayToBST(int[] nums) {
        return toBst(nums, 0, nums.length-1);
    }

    //My soln.
    private TreeNode toBst(int[] nums, int start, int end) {
        if (start > end) return null;
        if (start == end) return new TreeNode(nums[start]);

        /**
         Both ways of calculating mid will give
         different but correct BSTs.
         */
        // int mid = (start+end)/2;
        int mid = (int)Math.ceil((start + end) / 2.0);
        TreeNode node = new TreeNode(nums[mid]);
        node.left = toBst(nums, start, mid-1);
        node.right = toBst(nums, mid+1, end);
        return node;
    }
}
