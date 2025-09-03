package lc_potd;

import java.util.Arrays;
import java.util.Comparator;

//@link - https://leetcode.com/problems/find-the-number-of-ways-to-place-people-ii/
public class FindNumberOfWaysToPlacePeopleII {
    public int numberOfPairs(int[][] points) {
        return pass1(points);
    }

    /**
     * My soln. Mik also had a similar
     * approach.
     * However, mik's non sort approach was
     * O(n^3) while with sorting it was O(n^2)
     * So check that approach when revising.
     */
    private int pass1(int[][] points) {
        int n = points.length;

        Comparator<int[]> cmp1 = (a1, a2) -> a1[0] - a2[0];
        Comparator<int[]> cmp2 = (a1, a2) -> a2[1] - a1[1];
        Arrays.sort(points, cmp1.thenComparing(cmp2));
        int pairs = 0;

        /**
         Sorting helps in reducing the number
         of points that need to be checked for in-between.
         Can be solved without sorting also but will need
         to check all pairs.
         */
        for (int i=0; i<n; i++) {
            int ax = points[i][0];
            int ay = points[i][1];

            for (int j=i+1; j<n; j++) {
                int bx = points[j][0];
                int by = points[j][1];

                if (ay < by) continue;

                boolean inBetween = false;

                /*
                 * When sorting, this loop can
                 * be removed. Check mik's explanation
                 * */
                for (int k=i+1; k<j; k++) {
                    int kx = points[k][0];
                    int ky = points[k][1];
                    if (kx >= ax && kx <= bx && ky <= ay && ky >= by) {
                        inBetween = true;
                        break;
                    }
                }

                if (!inBetween) pairs += 1;
            }
        }

        return pairs;
    }
}
