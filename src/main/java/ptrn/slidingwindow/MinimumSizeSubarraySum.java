package ptrn.slidingwindow;

//@link - https://leetcode.com/problems/minimum-size-subarray-sum/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5293912693669888
public class MinimumSizeSubarraySum {
    public int minSubArrayLen(int target, int[] nums) {
//        return edctvSol(target, nums);
        return slidingWindowTemplate(target, nums);
    }

    /*
        Re-wrote to match my sliding window template
     */
    private int slidingWindowTemplate(int p, int[] nums) {
        int n = nums.length;
        int l=0, r=0, sum=0, len = Integer.MAX_VALUE;

        while (l < n) {
            while (r < n && sum < p) {
                sum += nums[r];
                r += 1;
            }

            if (sum >= p) {
                len = Math.min(len, r-l);
            }

            sum -= nums[l];
            l += 1;
        }

        return len == Integer.MAX_VALUE ? 0: len;
    }

    private int edctvSol(int target, int[] nums) {
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
