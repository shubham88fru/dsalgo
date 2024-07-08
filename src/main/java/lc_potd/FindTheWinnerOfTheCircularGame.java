package lc_potd;

import java.util.ArrayDeque;
import java.util.Deque;

//@link - https://leetcode.com/problems/find-the-winner-of-the-circular-game/
public class FindTheWinnerOfTheCircularGame {
    public int findTheWinner(int n, int k) {
        return okayish(n, k);
    }

    private int okayish(int n, int k) {
        int start = 1;
        Deque<Integer> q = new ArrayDeque<>();
        for (int i=1; i<=n; i++) q.addLast(i);

        while(q.size() > 1) {
            int i = k;
            while (i > 0 ) {
                int polled = q.removeFirst();

                //dont' add the last element back to q.
                if (i != 1) q.addLast(polled);
                i -= 1;
            }
        }

        return q.removeLast();
    }
}
