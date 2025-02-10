package lc_potd;

//@link - https://leetcode.com/problems/koko-eating-bananas/description/
public class KokoEatingBananas {
    public int minEatingSpeed(int[] piles, int h) {
        return pass1(piles, h);
    }

    /*
        My Solution. Binary search on answer.
    */
    private int pass1(int[] piles, int h) {
        int n = piles.length;

        int mx = Integer.MIN_VALUE;
        for (int pile: piles) {
            mx = Math.max(mx, pile);
        }

        int l = 1;
        int r = mx;

        int ans = mx;
        while (l <= r) {
            int mid = l + (r-l)/2;

            long hours = eat(mid, piles);
            if (hours <= h) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return ans;
    }

    private long eat(int mid, int[] piles) {
        int n = piles.length;

        long count = 0;
        for (int i=0; i<n; i++) {
            count += (long)Math.ceil(piles[i]/(mid*1.0));
        }

        return count;
    }
}
