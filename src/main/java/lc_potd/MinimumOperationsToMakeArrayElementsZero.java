package lc_potd;

import java.util.Collections;
import java.util.PriorityQueue;

//@link - https://leetcode.com/problems/minimum-operations-to-make-array-elements-zero/description/
//@check - https://www.youtube.com/watch?v=VYDl3p9bkkw&ab_channel=codestorywithMIK
public class MinimumOperationsToMakeArrayElementsZero {
    public long minOperations(int[][] queries) {
        // return brute(queries);
        // return better(queries);
        return optimal(queries);
    }

    /*
        Based on mik's explanation, coded by me.
     */
    private long optimal(int[][] queries) {
        int n = queries.length;

        long count = 0;
        for (int[] query: queries) {
            int start = query[0];
            int end = query[1];

            long queryTotal = 0;
            for (long pow=0, steps=1; Math.pow(4.0, pow) <= end; pow++, steps++) {
                long L = (long)Math.pow(4.0, pow);
                long R = (long)Math.pow(4.0, pow+1)-1;

                long l = Math.max(start, L);
                long r = Math.min(end, R);

                if (l > r) continue;
                queryTotal += (r-l+1)*steps;
            }

            count += Math.ceil(queryTotal/2.0);
        }

        return count;
    }

    /**
     To me, extemely counterintuitive, but apparently
     the total ops to reduce all elements to 0 in a list
     will be half of the sum of ops to reduce individual
     els to zero (reason being that we can select and reduce
     two elements together)
     */
    private long better(int[][] queries) {
        int n = queries.length;

        int count = 0;
        for (int[] query: queries) {
            int start = query[0];
            int end = query[1];

            int individualCount = 0;
            for (int i=start; i<=end; i++) {
                individualCount += ((int)(Math.log(i)/Math.log(4)) + 1); //ops to reduce indivual to zero
            }
            count += Math.ceil(individualCount/2.0);
        }

        return count;
    }

    /**
     I had a hunch but couldn't
     think that we need to keep picking
     the largest two nums. My initial
     intuition was to pick the smallest nums
     and make them zero fast. However, the
     problem with that approach is that towards,
     the end, we'll be left with larger numbers
     and all zeros, and we'll waste our capability
     of reducing two nums. Therefore, its wise to
     pick large numbers and keep reducing them,
     so that for the most of the attempts, we have
     two nums that we can reduce.
     */
    private long brute(int[][] queries) {
        int n = queries.length;

        int count = 0;
        for (int[] query: queries) {
            int start = query[0];
            int end = query[1];

            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
            for (int i=start; i<=end; i++) pq.add(i);

            while (!pq.isEmpty()) {
                int f = (int)Math.floor(pq.remove()/4);

                int s = 0;
                if (!pq.isEmpty()) s = (int)Math.floor(pq.remove()/4);

                if (f != 0) pq.add(f);
                if (s != 0) pq.add(s);
                count += 1;
            }
        }

        return count;
    }
}
