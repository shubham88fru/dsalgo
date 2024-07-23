package lc_potd;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

//@link - https://leetcode.com/problems/sort-array-by-increasing-frequency/
public class SortArrayByIncreasingFrequency {
    public int[] frequencySort(int[] nums) {
        Map<Integer, Integer> mp = new HashMap<>();
        for (int num: nums) {
            mp.put(num, mp.getOrDefault(num, 0)+1);
        }

        Comparator<int[]> cmp1 = (a1, a2) -> a1[1] - a2[1];
        Comparator<int[]> cmp2 = (a1, a2) -> a2[0] - a1[0];
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(cmp1.thenComparing(cmp2));
        for (Map.Entry<Integer, Integer> entry: mp.entrySet()) {
            minHeap.add(new int[] {entry.getKey(), entry.getValue()});
        }

        int[] ans = new int[nums.length];
        int i=0;
        while (!minHeap.isEmpty()) {
            int[] pair = minHeap.remove();
            int freq = pair[1];
            int num = pair[0];
            while (freq > 0) {
                ans[i] = num;
                i += 1;
                freq -= 1;
            }
        }

        return ans;
    }
}
