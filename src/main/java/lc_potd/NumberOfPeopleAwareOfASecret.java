package lc_potd;

import java.util.ArrayDeque;
import java.util.Deque;

public class NumberOfPeopleAwareOfASecret {
    public int peopleAwareOfSecret(int n, int delay, int forget) {
        return pass1(n, delay, forget);
    }

    /*
        My naive q approach.
        Works for smaller inputs
        but TLE for large inputs.
     */
    private int pass1(int n, int delay, int forget) {
        Deque<int[]> q = new ArrayDeque<>();

        q.addLast(new int[] {1+delay, forget});
        for (int i=1; i<=n; i++) {
            if (q.isEmpty()) break;
            int sz = q.size();

            while (sz > 0) {
                int[] curr = q.removeFirst();
                if (curr[1] >= i) {
                    q.addLast(curr);
                }
                if (curr[0] > i || curr[1] < i) {
                    sz -= 1;
                    continue;
                }

                q.addLast(new int[]{i+delay, i+forget-1});
                sz -= 1;
            }
        }

        return q.size()%1000000007;
    }
}
