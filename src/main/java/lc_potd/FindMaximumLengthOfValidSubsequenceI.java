package lc_potd;

//@link - https://leetcode.com/problems/find-the-maximum-length-of-valid-subsequence-i/description/
//@check - https://www.youtube.com/watch?v=u-NffNeqNqk&ab_channel=codestorywithMIK
public class FindValidLengthOfValidSubsequenceI {
    public int maximumLength(int[] nums) {
        //return pass1(nums);
        return pass2(nums);
    }

    /*
        Based on mik's hint.
        There's probably a bleak
        chance that I could have come up
        with this approach after many hours
        of trying.

        The idea is that, x%2 can only have
        two possible values - 0 and 1.
        Additionally, 0 means even+even/odd+odd,
        and 1 means even+odd/odd+even.
        Now, that way the equation is given,
        the problem boils down to finding longest stream
        of nums such that either all are even, all are odd,
        or are odd-even alternating.
    */
    private int pass2(int[] nums) {
        int n = nums.length;

        //all odds/evens
        int allEvens = 0;
        int allOdds = 0;
        for (int num: nums) {
            if (num%2==0) allEvens += 1;
            else allOdds += 1;
        }

        //alternating odd-even/even-odd
        int oddEven = 0;
        int evenOdd = 0;
        boolean even = true;
        boolean odd = true;
        for (int i=0; i<n; i++) {
            if ((odd && nums[i]%2 != 0) || (!odd && nums[i]%2 == 0)) {
                oddEven += 1;
                odd = !odd;
            }

            if ((even && nums[i]%2==0) || (!even && nums[i]%2 != 0)) {
                evenOdd += 1;
                even = !even;
            }

        }

        return Math.max(allEvens, Math.max(allOdds, Math.max(oddEven, evenOdd)));

    }

    /*
        Top-down DP was my initial intuition
        but gives TLE/MLE.
        Following is a correct implementation, though.
    */
    private int pass1(int[] nums) {
        int n = nums.length;
        Integer[][][] memo = new Integer[n+1][n+1][n+1];
        return dp(nums, -1, -1, 0, memo);
    }

    private int dp(int[] nums, int pi, int ppi, int i, Integer[][][] memo) {

        if (i >= nums.length) return 0;

        if (pi != -1 && ppi != -1 && memo[i][pi][ppi] != null) return memo[i][pi][ppi];

        int pick = 0;
        if (pi != -1 && ppi != -1) {
            if ((nums[pi]+nums[ppi])%2 == (nums[pi]+nums[i])%2) {
                pick += (1 + dp(nums, i, pi, i+1, memo));
            }
        } else if (pi != -1) {
            pick += (2 + dp(nums, i, pi, i+1, memo));
        } else {
            pick += dp(nums, i, pi, i+1, memo);
        }


        int npick = dp(nums, pi, ppi, i+1, memo);

        if (pi != -1 && ppi != -1) {
            memo[i][pi][ppi] = Math.max(pick, npick);
        }
        return Math.max(pick, npick);
    }
}
