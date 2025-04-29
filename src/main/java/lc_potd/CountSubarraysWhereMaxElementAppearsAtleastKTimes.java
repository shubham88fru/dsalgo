package lc_potd;

//@link - https://leetcode.com/problems/count-subarrays-where-max-element-appears-at-least-k-times/description/
public class CountSubarraysWhereMaxElementAppearsAtleastKTimes {
    public long countSubarrays(int[] nums, int k) {
        return revise(nums, k);
    }

    /*
    * Sliding window template 1: Always prefer this one.
    * */
    private long revise(int[] nums, int k) {
        int n = nums.length;

        int max = Integer.MIN_VALUE;
        for (int i=0; i<n; i++) {
            max = Math.max(max, nums[i]);
        }

        int l = 0;
        int r = 0;
        long subCount = 0;
        long maxCount = 0;

        while (r < n) {
            if (nums[r] == max) maxCount += 1;

            while (maxCount >= k) {
                subCount += (long)(n-r);
                if (nums[l] == max) maxCount -= 1;
                l += 1;
            }

            r += 1;
        }

        return subCount;
    }

    /*
    * Sliding window template 2: Avoid if possible.
    * Older sliding window template.
    * Sometimes needs handling for edges cases.
    * */
    private long slidingWindow(int[] nums, int k) {
        int n = nums.length;
        int l = 0;
        int r = 0;
        long count = 0;
        int seen = 0;

        //Find the max.
        long max = Integer.MIN_VALUE;
        for (long num: nums) {
            if (num>max) max = num;
        }

        //Sliding window.
        //NOTE: the iteration goes till l < n
        //as opposed to r < n  which is usually the case for
        //sliding window problems. This is because, in this problem,
        //even when r == n, we may have valid subarrays between l to n.
        while (l < n) {
            if (seen < k && r < n) {
                //increment the count of maxes seen so far.
                if (nums[r] == max) seen += 1;
                r += 1;
            } else if (seen >= k){
                //If seen atleast k times,
                //don't need to move the right pointer any further,
                //coz, all the subsequent subarrays will be valid.
                //So, directly add them to the count and release the left
                //to look for more subarrays.
                count += (n-r+1);
                int toRelease = nums[l];
                //if element to be release is max,
                //decrement the seen count.
                if (toRelease == max) seen -= 1;
                l += 1;
            } else l += 1;
        }

        return count;

    }
}
