package ptrn.dp;

//@link - https://leetcode.com/problems/maximum-product-subarray/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6255280159195136
//       - https://www.youtube.com/watch?v=hnswaLJvr6g&ab_channel=takeUforward
//@tag - TO_REVISIT
public class MaximumProductSubarray {
    public int maxProduct(int[] nums) {
//        return edctv(nums);
        return strvr(nums);
    }

    /*
        Brute force will be to generate all subarrays
        and subarrays and pick the max product subarray.

        Following is based on strvr's approach and I think
        this is the approach to go for in the interview.
        Others (edctv, nc) are a bit confusing.
    */
    private int strvr(int[] nums) {
        int n = nums.length;

        int prefix = 1;
        int suffix = 1;

        int max = Integer.MIN_VALUE;
        for (int i=0; i<n; i++) {
            if (prefix == 0) prefix = 1;
            if (suffix == 0) suffix = 1;

            prefix *= nums[i];
            suffix *= nums[n-1-i];

            max = Math.max(max, Math.max(prefix, suffix));
        }

        return max;
    }

    private int edctv(int[] nums) {
        if (nums.length == 0) return 0;

        int maxSoFar = nums[0];
        int minSoFar = nums[0];
        int result = maxSoFar;

        for (int i=1; i<nums.length; i++) {
            int curr = nums[i];
            int tempMaxSoFar = Math.max(curr, Math.max(maxSoFar*curr, minSoFar*curr));
            minSoFar = Math.min(curr, Math.min(maxSoFar*curr, minSoFar*curr));
            maxSoFar = tempMaxSoFar;

            result = Math.max(maxSoFar, result);
        }

        return result;
    }
}
