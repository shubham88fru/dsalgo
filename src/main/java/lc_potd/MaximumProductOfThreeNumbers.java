package lc_potd;

import java.util.Arrays;

//@link - https://leetcode.com/problems/maximum-product-of-three-numbers/description/?
public class MaximumProductOfThreeNumbers {
    public int maximumProduct(int[] nums) {
        // return brute(nums);
        return suboptimal(nums);
    }

    /*
        Took slight hint.
        This ain't the best approach
        though. Optimal approach is to not sort,
        scan and keep track of the nums needed.
    */
    private int suboptimal(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        int sPdt = nums[0]*nums[1]*nums[n-1]; //smallest with larget +ve.
        int lPdt = nums[n-1]*nums[n-2]*nums[n-3]; //largest pdt.

        return Math.max(sPdt, lPdt);
    }

    private int brute(int[] nums) {
        int n = nums.length;
        int pdt = Integer.MIN_VALUE;
        for (int i=0; i<n; i++) {
            for (int j=i+1; j<n; j++) {
                for (int k=j+1; k<n; k++) {
                    pdt = Math.max(pdt, nums[i]*nums[j]*nums[k]);
                }
            }
        }

        return pdt;
    }
}
