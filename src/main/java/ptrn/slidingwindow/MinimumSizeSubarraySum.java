package ptrn.slidingwindow;

//@link - https://leetcode.com/problems/minimum-size-subarray-sum/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5293912693669888
public class MinimumSizeSubarraySum {
    public int minSubArrayLen(int target, int[] nums) {
        int start = 0;
        int end  = 0;
        int n = nums.length;
        int sum = 0;
        int minLen = Integer.MAX_VALUE;

        //till the end of the array.
        while (end < n) {
            //add the num to sum..
            sum += nums[end];

            //if sum becomes greater than target,
            //start looking for minimal subarray that just keeps
            //the sum above target.
            while (sum >= target) {
                //record current length.
                minLen = Math.min(minLen, end-start+1);

                //release the from left end.
                //Since the element is release, also
                //decrease the sum.
                sum -= nums[start];

                //release.
                start += 1;
            }

            //increase the window.
            end += 1;
        }

        return minLen == Integer.MAX_VALUE ? 0: minLen;
    }
}
