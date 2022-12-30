package swd.hashmap;

import java.util.HashMap;
import java.util.Map;

//@link - https://www.codingninjas.com/codestudio/problems/subarrays-with-zero-sum_3161876?leftPanelTab=1
public class CountSubArrayWithZeroSum {
    public static int countSubarrays(int n, int[] arr) {
        // Write your code here.
        Map<Integer, Integer> memo = new HashMap<>();
        int prefixSum = 0;
        int res = 0;
        memo.put(prefixSum, 1); //0 has appeared once (at -1)

        for (int el: arr) {
            prefixSum += el;
            if (memo.containsKey(prefixSum)) {
                int occurrence = memo.get(prefixSum);
                res += occurrence;
                memo.put(prefixSum, occurrence+1);
            } else memo.put(prefixSum, 1); //first time seeing.
        }

        return res;
    }

}
