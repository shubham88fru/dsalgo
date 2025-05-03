package lc_potd;

//@link - https://leetcode.com/problems/minimum-domino-rotations-for-equal-row/submissions/1624410311/
public class MinimumDominoRotationsForEqualRow {
    public int minDominoRotations(int[] tops, int[] bottoms) {
        // return pass1(tops, bottoms);
        return pass2(tops, bottoms);
    }

    /*
        Coded by me but took a slight hint from
        the discussion.

        Intuition: Since the numbers on the domino are
        limited (i.e. 1-6), for each array try if we can
        make each value 1-6.

        Note: Whenever, a problem hints at a limited set of
        input values (in this case 1-6), its often hinting
        at a brute-force/greedy soln.
    */
    private int pass1(int[] tops, int[] bottoms) {
        int n = tops.length;

        int min1 = Integer.MAX_VALUE;
        for (int i=1; i<=6; i++) {
            int swaps = 0;
            for (int j=0; j<n; j++) {
                if (tops[j] == i) continue;
                if (bottoms[j] != i) {
                    swaps = Integer.MAX_VALUE;
                    break;
                } else {
                    swaps += 1;
                }
            }
            min1 = Math.min(min1, swaps);
        }

        int min2 = Integer.MAX_VALUE;
        for (int i=1; i<=6; i++) {
            int swaps = 0;
            for (int j=0; j<n; j++) {
                if (bottoms[j] == i) continue;
                if (tops[j] != i) {
                    swaps = Integer.MAX_VALUE;
                    break;
                } else {
                    swaps += 1;
                }
            }
            min2 = Math.min(min2, swaps);
        }

        int ans = Math.min(min1, min2);
        return ans == Integer.MAX_VALUE ? -1: ans;
    }

    /*
    * Optimization to pass1 solution above.
    * Single pass - took hint from mik.
    *  */
    private int pass2(int[] tops, int[] bottoms) {
        int n = tops.length;

        int ans = Integer.MAX_VALUE;
        for (int i=1; i<=6; i++) {
            int swapsTop = 0;
            int swapsBottom = 0;
            for (int j=0; j<n; j++) {
                if (bottoms[j] != i && tops[j] != i) {
                    swapsTop = Integer.MAX_VALUE;
                    swapsBottom = Integer.MAX_VALUE;
                    break;
                } else if (bottoms[j] != i) {
                    swapsBottom += 1;
                } else if (tops[j] != i) {
                    swapsTop += 1;
                }
            }
            ans = Math.min(ans, Math.min(swapsBottom, swapsTop));

        }

        return ans == Integer.MAX_VALUE ? -1: ans;
    }
}
