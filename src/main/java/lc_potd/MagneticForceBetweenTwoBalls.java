package lc_potd;

import java.util.Arrays;

//@link - https://leetcode.com/problems/magnetic-force-between-two-balls/
//@check - https://www.youtube.com/watch?v=MXnZPSPqkBM&ab_channel=AryanMittal
public class MagneticForceBetweenTwoBalls {
    public int maxDistance(int[] position, int m) {
        return binarySearch(position, m);
    }

    /**
     The sentence `minimum magnetic force should be maximum`
     should be a strong hint to use binary search in this problem.
     Note that positions array tells the positions as which buckets are
     available to put the balls in.
     */

    /**
     Idea is that we'll first find the max possible distance between any two balls
     i.e. max-min, and also the min distance possible between any two balls i.e. 1.
     Then starting from the max distance, we'll keep checking if with that particular
     distance between consecutive balls are we able to put all available balls into the
     buckets. If yes, that is our answer. Otherwise, we'll try for a lower distance.

     */

    private int binarySearch(int[] position, int m) {
        //First sort the array, so that we actually place each
        //successive balls at a desired distance from the prev placed ball.
        Arrays.sort(position);

        int n = position.length;
        int l = 1;
        int r = position[n-1]-position[0];

        int maxDist = 0;
        while (l <= r) {
            int mid = l + (r-l) / 2;
            if (canPlaceAllBallAtGivenDist(mid, position, m)) {
                l = mid + 1;
                maxDist = mid;
            } else {
                r = mid - 1;
            }
        }

        return maxDist;
    }

    private boolean canPlaceAllBallAtGivenDist(int dist, int[] position, int m) {

        //place first ball at the leftmost pos.
        int prev = position[0];
        m -= 1;

        for (int i=1; i<position.length; i++) {
            if (position[i]-prev >= dist) {
                prev = position[i];
                m -= 1;
            }

            if (m <= 0) return true;
        }

        return false;
    }
}
