package lc_potd;

import java.util.Comparator;
import java.util.PriorityQueue;

//@link - https://leetcode.com/problems/rank-transform-of-an-array/
public class RankTransformOfAnArray {

    public int[] arrayRankTransform(int[] arr) {
        return revise(arr);
    }

    //My soln, I don't think is the most
    //optimal though.
    private int[] revise(int[] arr) {
        int n = arr.length;
        Comparator<int[]> cmp = (c1, c2) -> c1[0] - c2[0];
        PriorityQueue<int[]> pq = new PriorityQueue<>(cmp);

        for (int i=0; i<n; i++) {
            pq.add(new int[]{arr[i], i});
        }

        int[] ranks = new int[n];
        int prev=Integer.MIN_VALUE, rank=0;
        while (!pq.isEmpty()) {
            int[] p = pq.remove();

            if (p[0] != prev) rank += 1;
            ranks[p[1]] = rank;
            prev = p[0];
        }

        return ranks;
    }
}
