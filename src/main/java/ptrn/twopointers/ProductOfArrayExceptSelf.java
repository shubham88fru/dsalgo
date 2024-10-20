package ptrn.twopointers;

//@link - https://leetcode.com/problems/product-of-array-except-self/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/4779083553832960
public class ProductOfArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        return revise(nums);
    }

    /*
        My soln. I guess this on the optimal track,
        but LC followup says that this could be
        solved in O(1) extra space. So, my soln might
        not be the best soln. Therefore, if this problem
        is reoccurring problem for some company, then
        check YT for an even better soln.
     */
    private int[] revise(int[] nums) {
        int n = nums.length;
        int[] prefixProduct = new int[n];
        int[] suffixProduct = new int[n];

        prefixProduct[0] = 1;
        suffixProduct[n-1] = 1;

        for (int i=1; i<n; i++) {
            prefixProduct[i] = prefixProduct[i-1] * nums[i-1];
        }

        for (int i=n-2; i>=0; i--) {
            suffixProduct[i] = suffixProduct[i+1] * nums[i+1];
        }

        int[] ans = new int[n];
        for (int i=0; i<n; i++) {
            ans[i] = (prefixProduct[i] * suffixProduct[i]);
        }

        return ans;
    }
}
