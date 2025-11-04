package lc_potd;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

//@link - https://leetcode.com/problems/find-x-sum-of-all-k-long-subarrays-i/description/?
public class FindXSumOfAllKLongSubarraysI {
    public int[] findXSum(int[] nums, int k, int x) {
        return pass1(nums, k, x);
    }

    /*
        My soln.
     */
    private int[] pass1(int[] nums, int k, int x) {
        int n = nums.length;

        int[] ans = new int[n-k+1];
        Map<Integer, Integer> window = new HashMap<>();

        //sliding window
        int l = 0;
        int r = 0;
        int idx = 0;
        while (r < n) {
            while (r - l < k) {
                window.put(nums[r], window.getOrDefault(nums[r], 0)+1);
                r += 1;
            }

            ans[idx++] = calc(window, x);

            int toRem = nums[l];
            window.put(toRem, window.get(toRem)-1);
            if (window.get(toRem) == 0) window.remove(toRem);
            l += 1;
        }


        return ans;
    }

    private int calc(Map<Integer, Integer> freqs, int x) {

        /**
         Note: min heap (reverse of what's needed) to
         implement top-k freq.
         */
        Comparator<int[]> cmp1 = (a1, a2) -> a1[1] - a2[1];
        Comparator<int[]> cmp2 = (a1, a2) -> a1[0] - a2[0];
        Comparator<int[]> combined = cmp1.thenComparing(cmp2);

        PriorityQueue<int[]> pq = new PriorityQueue<>(combined);

        for (Map.Entry<Integer, Integer> entry: freqs.entrySet()) {
            int[] item = new int[]{entry.getKey(), entry.getValue()};

            //note how to compare using comparator.
            /**
                 if (pq.size() < x) {
                    pq.add(item);
                 } else if (combined.compare(pq.peek(), item) < 0) {
                     pq.remove();
                     pq.add(item);
                 }
             */

            //this is smart
            pq.add(item);
            if (pq.size() > x) pq.remove();
        }

        int sum = 0;
        while (x > 0 && !pq.isEmpty()) {
            int[] rem = pq.remove();
            sum += (rem[0]*rem[1]);
            x -= 1;
        }

        return sum;
    }
}
