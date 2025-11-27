package ptrn.slidingwindow;

//@link - https://leetcode.com/problems/maximum-subarray/description/
//@strvr - https://takeuforward.org/data-structure/kadanes-algorithm-maximum-subarray-sum-in-an-array/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5734363859517440
public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        //return maxSumSubArrayBrute(nums);
        //return maxSumSubArrayBetter(nums);
        return maxSubArraySumOptimal(nums);
    }

    //1) Optimal Solution (using kadane's algorithm). T: O(N), S: O(1)
    private int maxSubArraySumOptimal(int[] nums) {
        int sum = 0;
        int maxSum = Integer.MIN_VALUE;

        for (int i=0; i<nums.length; i++) {
            sum += nums[i];

            if (sum > maxSum) maxSum = sum;

            //if at any moment, sum becomes -ve
            //there's no point taking it to next
            //iteration for addition, since it is only
            //going to reduce whatever we add to it (+ve or -ve).
            //so we initialize it to zero, so that in next iteration
            //sum becomes equal to next element.
            if (sum < 0) sum = 0;
        }

        return maxSum;
    }

    //2) Better approach. T: O(N^2), S: O(1)
    //Instead of recalculating the sum again and again
    //in the inner most loop (see brute force), we store the
    //sum in a prev sum variable and simply add the curr el to
    //get the curr sub array's sum.
    private int maxSumSubArrayBetter(int[] nums) {
        int n = nums.length;
        int maxSum = Integer.MIN_VALUE;

        for (int i=0; i<n; i++) {
            int sum = 0;
            for (int j=i; j<n; j++) {
                sum += nums[j];
                maxSum = Math.max(maxSum, sum);
            }
        }

        return maxSum;
    }

    //1) Brute force. T: O(N^3), S: O(1)
    private int maxSumSubArrayBrute(int[] nums) {
        int n = nums.length;
        int maxSum = Integer.MIN_VALUE;

        for (int i=0; i<n; i++) {
            for (int j=i; j<n; j++) {
                int sum = 0;
                for (int k=i; k<=j; k++) {
                    sum += nums[k];
                }
                maxSum = Math.max(maxSum, sum);
            }
        }

        return maxSum;
    }
}
