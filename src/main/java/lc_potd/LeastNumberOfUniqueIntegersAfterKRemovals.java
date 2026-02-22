package lc_potd;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

//@link - https://leetcode.com/problems/least-number-of-unique-integers-after-k-removals/?
public class LeastNumberOfUniqueIntegersAfterKRemovals {
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        return pass1(arr, k);
    }

    private int pass1(int[] arr, int k) {
        int n = arr.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a1, a2) -> a1[1]-a2[1]);

        Map<Integer, Integer> freq = new HashMap<>();
        for (int a: arr) {
            freq.put(a, freq.getOrDefault(a, 0)+1);
        }

        for (Map.Entry<Integer, Integer> entry: freq.entrySet()) {
            pq.add(new int[]{entry.getKey(), entry.getValue()});
        }

        while (k > 0 && !pq.isEmpty()) {
            int[] pair = pq.remove();
            if (pair[1] <= k) {
                freq.remove(pair[0]);
                k -= pair[1];
            } else break;
        }

        return freq.size();
    }
}
