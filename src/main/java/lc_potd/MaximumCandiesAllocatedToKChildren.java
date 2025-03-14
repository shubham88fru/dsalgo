package lc_potd;

//@link - https://leetcode.com/problems/maximum-candies-allocated-to-k-children/description/
public class MaximumCandiesAllocatedToKChildren {
    public int maximumCandies(int[] candies, long k) {
        return pass1(candies, k);
    }

    //1) Using Binary search. Mik had the same intuition.
    //TC: n*log(maxCandy)
    //SC: O(1)
    private int pass1(int[] candies, long k) {
        //The max number of candies a child can get
        //is the max of all candies.
        long max = Integer.MIN_VALUE;
        for (int candy: candies) max = Math.max(max, candy);

        long l = 1;
        long r = max;

        int maxCandy = 0;
        while (l <= r) {
            long mid = l + (r-l)/2;
            if (canAssign(candies, mid, k)) {
                maxCandy = (int)mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return maxCandy;
    }

    private boolean canAssign(int[] candies, long count, long k) {

        for (int candy: candies) {
            k -= (int)(candy/count);
            if (k <= 0) return true;
        }

        return false;
    }

    //2) Brute force.
    //The max candies any child can get is the max of all candies.
    //so start by assuming that the answer is max and see if each
    //pile can be broken into piles of max candies. If so, then that
    //is the answer, otherwise, check a lower value and so on untill 1,
    //coz each child should receive atleast one candy.
}
