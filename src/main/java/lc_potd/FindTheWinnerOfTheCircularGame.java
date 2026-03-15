package lc_potd;

import java.util.ArrayDeque;
import java.util.Deque;

//@link - https://leetcode.com/problems/find-the-winner-of-the-circular-game/
//@check - https://www.youtube.com/watch?v=lq3RMxKST-Y
public class FindTheWinnerOfTheCircularGame {
    public int findTheWinner(int n, int k) {
        // return queue(n, k);
        return recursion(n, k);
    }

    /*
        Recursive soln coded by me, based
        on mik's explanation.
     */
    private int recursion(int n, int k) {
        return getIdx(n, k) + 1; //coz numbered from 1 to n.
    }

    private int getIdx(int n, int k) {
        if (n == 1) return 0; //If only one element in array, idx 0 is winner.
        int newIdx = getIdx(n-1, k); //idx in the shrunk array of size n-1
        int ogIdx = (newIdx+k)%n; //convert to idx of the og array of size n
        return ogIdx;
    }

    private int queue(int n, int k) {
        Deque<Integer> q = new ArrayDeque<>();
        for (int i=1; i<=n; i++) q.addLast(i);

        while (q.size() > 1) {
            int toRem=k;

            //remove k-1 and append
            //to end of q.
            while (toRem > 1) {
                int i = q.removeFirst();
                q.addLast(i);
                toRem -= 1;
            }

            //remove kth.
            q.removeFirst();
        }

        return q.removeFirst();
    }
}
