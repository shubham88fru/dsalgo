package lc_potd;

//@link - https://leetcode.com/problems/number-of-zero-filled-subarrays/description/
public class NumberOfZeroFilledSubarrays {
    public long zeroFilledSubarray(int[] nums) {
        return pass1(nums);
    }

    /*
        My first intuition (except)
        for brute force was to sliding
        window, However, we don't really
        need sliding window here.

        Note: Mik showed an additional
        interesting soln. revise that
        approach if this is a frequently
        occurring problem for some
        company.
    */
    private long pass1(int[] nums) {
        int n = nums.length;

        int l = 0;
        int r = 0;
        long cnt = 0;
        while (r < n) {
            while (r < n && nums[r] == 0) {
                r += 1;
            }
            long m = r-l;
            cnt += m*((int)(m+1)/2.0);

            r += 1;
            l = r;
        }

        return cnt;
    }
}
