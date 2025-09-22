package lc_potd;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/count-elements-with-maximum-frequency/description/?
public class CountElementsWithMaximumFrequency {
    public int maxFrequencyElements(int[] nums) {
        return revise(nums);
    }

    /**
     * one pass soln.
     */
    private int revise(int[] nums) {
        Map<Integer, Integer> f1 = new HashMap<>();
        Map<Integer, Integer> f2 = new HashMap<>();

        int maxFreq = 0;
        for (int num: nums) {
            f1.put(num, f1.getOrDefault(num, 0)+1);
            if (f2.containsKey(f1.get(num)-1)) {
                f2.put(f1.get(num)-1, f2.get(f1.get(num)-1)-1);
            }
            f2.put(f1.get(num), f2.getOrDefault(f1.get(num), 0)+1);
            maxFreq = Math.max(f1.get(num), maxFreq);
        }

        return maxFreq*f2.get(maxFreq);
    }
}
