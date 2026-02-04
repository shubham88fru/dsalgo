package ptrn.twopointers;

//@link - https://leetcode.com/problems/product-of-array-except-self/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/4779083553832960
public class ProductOfArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        // return revise(nums);
        return optimal(nums);
    }

    /**
     Without using extra space.
     Idea is to use the ans array
     itself as the pp and then
     do another iteration of suffix.
     */
    private int[] optimal(int[] nums) {
        int n = nums.length;

        int[] ans = new int[n];
        ans[0] = 1;
        for (int i=1; i<n; i++) {
            ans[i] = ans[i-1]*nums[i-1];
        }

        int sp = 1;
        for (int i=n-1; i>=0; i--) {
            ans[i] = ans[i]*sp;
            sp *= nums[i];
        }

        return ans;
    }

    /**
     Using prefix and suffix arrays
     */
    private int[] revise(int[] nums) {
        int n = nums.length;

        int[] pp = new int[n];
        int[] sp = new int[n];
        pp[0] = 1;
        sp[n-1] = 1;

        for (int i=1; i<n; i++) {
            pp[i] = pp[i-1]*nums[i-1];
            sp[n-1-i] = sp[n-i]*nums[n-i];
        }

        int[] ans = new int[n];
        for (int i=0; i<n; i++) {
            ans[i] = pp[i]*sp[i];
        }

        return ans;
    }
}
