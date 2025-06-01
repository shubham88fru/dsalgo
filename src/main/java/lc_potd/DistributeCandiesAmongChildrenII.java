package lc_potd;

//@link - https://leetcode.com/problems/distribute-candies-among-children-ii/description/
//@check - https://www.youtube.com/watch?v=eL_3cDp0zjE&t=2341s&ab_channel=codestorywithMIK
public class DistributeCandiesAmongChildrenII {
    public long distributeCandies(int n, int limit) {
        // return brute(n, limit);
        // return better(n, limit);
        return optimal(n, limit);
    }

    /*
        Based on mik's explanation but confusing.
        Will need to rewatch
    */
    private long optimal(int n, int limit) {
        long ways = 0;
        int minCh1 = Math.max(0, n-2*limit);
        int maxCh1 = Math.min(n, limit);

        for (int i=minCh1; i<=maxCh1; i++) {
            int N = n - i; //remaining n for ch2 and ch3
            int minCh2 = Math.max(0, N-limit);
            int maxCh2 = Math.min(N, limit);

            ways += (maxCh2 - minCh2 + 1);
        }

        return ways;
    }

    private int brute(int n, int limit) {
        // return recursion(0, n, limit);
        return iterative(n, limit);
    }

    private int recursion(int curr, int n, int limit) {

        if (n == 0) return 1;
        if (curr >= 3) return 0;

        int total = 0;
        for (int i=0; i <= Math.min(n, limit); i++) {
            total += recursion(curr+1, n-i, limit);
        }

        return total;
    }

    private int iterative(int n, int limit) {
        int ways = 0;
        //n^3
        for (int ch1=0; ch1 <= Math.min(n, limit); ch1+=1) {
            for (int ch2=0; ch2 <= Math.min(n-ch1, limit); ch2 += 1) {
                for (int ch3=0; ch3 <= Math.min(n-ch1-ch2, limit); ch3 += 1) {
                    if (ch1 + ch2 + ch3 == n) ways += 1;
                }
            }
        }

        return ways;
    }

    //If we know the candies given to first two children,
    //we can know how much we "must" give to the third.
    //Note that for the third child we don't really have an option
    //because we "must" finish all the candies. So we give the third
    //child whatever is remaining and see if the candies sum up.
    private int better(int n, int limit) {
        int ways = 0;
        //n^2
        for (int ch1=0; ch1 <= Math.min(n, limit); ch1+=1) {
            for (int ch2=0; ch2 <= Math.min(n-ch1, limit); ch2 += 1) {
                int ch3 = Math.min(limit, n-ch1-ch2);
                if (ch1+ch2+ch3 == n) ways += 1;
            }
        }

        return ways;
    }
}
