package swd.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/delete-and-earn/
public class DeleteAndEarn {
    public int deleteAndEarn(int[] nums) {
        int[] freqs = getFreqArray(nums);
        return maxEarnForDelete(freqs, 0, new HashMap<Integer, Integer>());
    }

    private int maxEarnForDelete(int[] freqs, int currIndex, Map<Integer, Integer> memo) {
        if (currIndex >= freqs.length) return 0;

        int key = currIndex;
        if (memo.containsKey(key)) return memo.get(key);

        int deleteCurr = currIndex*freqs[currIndex] + maxEarnForDelete(freqs, currIndex+2, memo);
        int skipCurr = maxEarnForDelete(freqs, currIndex+1, memo);
        memo.put(key, Math.max(deleteCurr, skipCurr));
        return memo.get(key);
    }

    private int[] getFreqArray(int[] nums) {
        int freq = 1;
        int maxvalue = 0;
        for (int num: nums) {
            maxvalue = Math.max(num, maxvalue);
        }
        int[] freqs = new int[maxvalue+1];
        Arrays.sort(nums);
        for (int num: nums) {
            freqs[num] +=1;
        }
        // [3,4,2] --> [0, 0, 1, 1, 1]
        return freqs;
    }
}
