package lc_potd;

//@link - https://leetcode.com/problems/minimum-limit-of-balls-in-a-bag/description/
//@check - https://www.youtube.com/watch?v=5gtuhc-a_DQ&ab_channel=codestorywithMIK
public class MinimumLimitOfBallsInABag {
    public int minimumSize(int[] nums, int maxOperations) {
        /*
            "minimize the maximum" was a give away to use the binary search
            on answers pattern but still I coulnd't figure it out.
        */
        return mikssol(nums, maxOperations);
    }

    /*
        Coded by me but completely based on mik's explanation.
     */
    private int mikssol(int[] nums, int maxOperations) {
        int max = Integer.MIN_VALUE;
        for (int num: nums) max = Math.max(max, num);

        int l = 1;
        int r = max;
        int result = 1;
        while (l <= r) {
            int mid = l + (r-l) / 2;
            int ops = maxOperations;
            int res = mid;
            for (int num: nums) {
                int rem = num%mid;
                int times = num/mid;
                if (rem == 0) times -= 1; //edge case, explained by mik.
                if ((num > mid) && ops >= times) {
                    num -= times*mid;
                    ops -= times;
                }

                res = Math.max(res, num);
            }

            if (res > mid) {
                l = mid + 1;
            } else {
                r = mid - 1;
                result = res;
            }
        }

        return result;
    }
}
