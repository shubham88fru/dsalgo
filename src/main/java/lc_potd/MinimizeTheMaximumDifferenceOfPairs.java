package lc_potd;

import java.util.Arrays;

//@link - https://leetcode.com/problems/minimize-the-maximum-difference-of-pairs/description/
//@check - https://www.youtube.com/watch?v=OsG2i00LXFc&ab_channel=codestorywithMIK
public class MinimizeTheMaximumDifferenceOfPairs {
    public int minimizeMax(int[] nums, int p) {
        return mikssol(nums, p);
    }

    /*
        Coded by me but based on mik's
        explanation.
     */
    private int mikssol(int[] nums, int p) {
        int n = nums.length;

        Arrays.sort(nums);
        int l = 0;
        int r = Math.abs(nums[n-1]-nums[0]);

        int ans = 0;
        while (l <= r) {
            int mid = l + (r-l)/2;
            if (count(mid, nums) >= p) {
                ans = mid;
                r = mid - 1;
            } else l = mid + 1;
        }

        return ans;
    }

    private int count(int diff, int[] nums) {
        int count = 0;
        int n = nums.length;

        int i=0;
        while (i < n-1) {
            int absDiff = Math.abs(nums[i]-nums[i+1]);
            if (absDiff <= diff){
                count += 1;
                i += 2;
            } else {
                i += 1;
            }
        }

        return count;
    }
}
