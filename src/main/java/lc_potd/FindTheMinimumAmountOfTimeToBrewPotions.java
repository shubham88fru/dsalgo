package lc_potd;

//@link - https://leetcode.com/problems/find-the-minimum-amount-of-time-to-brew-potions/description/
//@check - https://www.youtube.com/watch?v=nCutrlJaRBo
public class FindTheMinimumAmountOfTimeToBrewPotions {
    public long minTime(int[] skill, int[] mana) {
        // return pass1(skill, mana);
        return mikssol(skill, mana);
    }

    /**
     Coded by me based on mik's and editorial soln.
     */
    private long mikssol(int[] skill, int[] mana) {
        int n = skill.length;
        int m = mana.length;

        long[] times = new long[n];
        for (int j=0; j<m; j++) {
            long time = 0;
            for (int i=0; i<n; i++) {
                time = Math.max(time, times[i]) + (long)skill[i]*mana[j];
            }

            times[n-1] = time;
            for (int i=n-2; i>=0; i--) {
                times[i] = times[i+1] - (long)(skill[i+1]*mana[j]);
            }
        }

        return times[n-1];
    }

    /**
     My Binary search approach.
     Gives TLE for last few cases.
     */
    private long pass1(int[] skill, int[] mana) {
        int n = skill.length;
        int m = mana.length;

        long[] times = new long[n];
        for (int i=0; i<n; i++) {
            if (i==0) times[i] = (long)skill[i]*mana[0];
            else {
                times[i] = times[i-1] + (long)(skill[i]*mana[0]);
            }
        }

        for (int i=1; i<m; i++) {
            times = binarySearch(times, skill, mana[i]);
        }

        return times[n-1];
    }

    private long[] binarySearch(long[] times, int[] skill, long mana) {
        int n = times.length;

        long l = times[0];
        long r = times[n-1];

        long[] newTimes = new long[n];
        while (l <= r) {
            long mid = l + (r-l)/2;
            long[] t_ = new long[n];
            boolean possible = true;
            for (int i=0; i<n; i++) {
                if (i==0) t_[i] = mid + (long)skill[i]*mana;
                else {
                    t_[i] = t_[i-1] + (long)(skill[i]*mana);
                }
                if (i < n-1) {
                    if (t_[i] < times[i+1]) {
                        possible = false;
                        break;
                    }
                }
            }
            if (possible) {
                r = mid - 1;
                newTimes = t_;
            } else {
                l = mid + 1;
            }
        }
        return newTimes;
    }
}
