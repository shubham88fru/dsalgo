package lc_potd;

//@link - https://leetcode.com/problems/maximize-the-minimum-powered-city/
//@check - https://www.youtube.com/watch?v=CAvYhc74Eio&t=3019s
public class MaximizeTheMinimumPoweredCity {
    public long maxPower(int[] stations, int r, int k) {
        return mikssol(stations, r, k);
    }

    /**
         Following is coded by me based on mik's
         explanation.

         Ofc, I had a hunch that this is going to be
         binary search (b/c 'maximize the minimum')
         but didn't know how to implement e2e.

         Idea is to use BS with Diff array technique.
     */
    private long mikssol(int[] stations, int r, int k) {
        int n = stations.length;
        long[] diff = new long[n];

        long sum = 0;
        long min = Long.MAX_VALUE;

        for (int i=0; i<n; i++) {
            sum += stations[i];
            min = Math.min(min, stations[i]);

            int l_ = Math.max(0, i-r);
            int r_ = i+r+1;
            diff[l_] += stations[i];
            if (r_ < n) diff[r_] -= stations[i];
        }

        long l_ = min; // min possible answer
        long r_ = sum + k; // max possible answer

        long ans = -1;
        while (l_ <= r_) {
            long mid = l_ + (r_-l_)/2;

            if (possible(mid, diff, k, r)) {
                ans = mid;
                l_ = mid + 1;
            } else r_ = mid - 1;
        }

        return ans;
    }

    private boolean possible(long ans, long[] ogDiff, int k, int r) {
        int n = ogDiff.length;

        long[] diff = new long[n];
        for (int i=0; i<n; i++) diff[i] = ogDiff[i];

        long power = 0;
        for (int i=0; i<n; i++) {

            power += diff[i]; //power of the ith city
            if (power < ans) {

                long need = ans - power;
                if (k < need) return false;

                //Greedily add `need` plants at
                //i+r so that it can
                //impact max num of cities.
                int l_ = i;
                int r_ = (i+(2*r))+1;
                power += need;
                if (r_ < n) diff[r_] -= need;
                k -= need;
            }

        }

        return true;
    }
}
