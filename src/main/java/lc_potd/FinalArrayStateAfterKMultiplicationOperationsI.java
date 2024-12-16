package lc_potd;

import java.util.Comparator;
import java.util.PriorityQueue;

//@link - https://leetcode.com/problems/final-array-state-after-k-multiplication-operations-i/
public class FinalArrayStateAfterKMultiplicationOperationsI {
    public int[] getFinalState(int[] nums, int k, int multiplier) {
        Comparator<int[]> cmp1 = (a1, a2) -> a1[0] - a2[0];
        Comparator<int[]> cmp2 = (a1, a2) -> a1[1] - a2[1];
        PriorityQueue<int[]> pq = new PriorityQueue<>(cmp1.thenComparing(cmp2));

        for (int i=0; i<nums.length; i++) pq.add(new int[] {nums[i], i});

        int[] ans = new int[nums.length];
        for (int i=0; i<k; i++) {
            int[] pair = pq.remove();
            pq.add(new int[]{pair[0]*multiplier, pair[1]});
        }

        while (!pq.isEmpty()) {
            int[] pair = pq.remove();
            ans[pair[1]] = pair[0];
        }

        return ans;
    }
}
