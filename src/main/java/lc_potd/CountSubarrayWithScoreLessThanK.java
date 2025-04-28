package lc_potd;

//@link - https://leetcode.com/problems/count-subarrays-with-score-less-than-k/description/
//@check - https://www.youtube.com/watch?v=wxd3SWgzoKA&ab_channel=codestorywithMIK
public class CountSubarrayWithScoreLessThanK {
    public long countSubarrays(int[] nums, long k) {
        return pass1(nums, k);
    }

    /*
    * My soln for the most part.
    * The part where we had to the count of
    * subarrays got me.
    * */
    private long pass1(int[] nums, long k) {
        int n = nums.length;
        int l = 0;
        int r = 0;

        long rs = 0;
        long score = 0;
        long count = 0;

        while (r < n) {
            rs += nums[r];
            score = (long)(rs * (r-l+1));

            while (score >= k) {
                rs -= nums[l];
                score = (long)(rs * (r-l));
                l += 1;
            }

            count += (long)(r-l+1); //damn - this line had me.

            r += 1;

        }

        return count;
    }
}
