package lc_potd;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

//@link - https://leetcode.com/problems/maximum-frequency-of-an-element-after-performing-operations-ii/description/?
//@check - https://www.youtube.com/watch?v=hSyXvam4Us4
public class MaximumFrequencyOfAnElementAfterPerformingOperationsII {
    public int maxFrequency(int[] nums, int k, int numOperations) {
        // return pass1(nums, k, numOperations);
        return modifiedDiffArrayTechnique(nums, k, numOperations);
    }

    /**
     Coded by me based on mik's explanation.
     */
    private int modifiedDiffArrayTechnique(int[] nums, int k, int numOperations) {
        int max = Integer.MIN_VALUE;
        for (int num: nums) max = Math.max(num+k, max); //target can be from 0..max+k

        TreeMap<Integer, Integer> map = new TreeMap<>();
        Map<Integer, Integer> ogFreq = new HashMap<>();

        for (int num: nums) {
            ogFreq.put(num, ogFreq.getOrDefault(num, 0)+1);

            int l = Math.max(0, num-k);
            int r = Math.min(max, num+k);

            map.put(l, map.getOrDefault(l, 0)+1);
            map.put(r+1, map.getOrDefault(r+1, 0)-1);
            map.put(num, map.getOrDefault(num, 0));
        }

        int runSum = 0;
        int maxFreq = 0;
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            int target = entry.getKey();
            int diff = entry.getValue() + runSum;
            map.put(entry.getKey(), entry.getValue()+runSum);
            runSum = map.get(entry.getKey());

            int targetFreq = ogFreq.getOrDefault(target, 0); //freq of target in og array.
            int numConversionsNeeded = diff - targetFreq;
            int maxTargetFreqPossible = targetFreq + Math.min(numOperations, numConversionsNeeded);

            maxFreq = Math.max(maxFreq, maxTargetFreqPossible);
        }

        return maxFreq;
    }

    /**
     This is the same code as part I of this problem,
     however, this won't submit coz of the constraints.
     The only diff between this part II and part I are
     the harder constraints.
     */
    private int pass1(int[] nums, int k, int numOperations) {
        int max = Integer.MIN_VALUE;
        for (int num: nums) max = Math.max(num+k, max);

        int[] freqs = new int[max+1];
        for (int num: nums) freqs[num] += 1;

        for (int i=1; i<freqs.length; i++) freqs[i] += freqs[i-1];

        int maxFreq = 0;
        for (int target=0; target<=max; target++) {
            int l = Math.max(0, target-k);
            int r = Math.min(max, target+k);

            int targetFreq = freqs[target] - (target == 0?0: freqs[target-1]);
            int numsThatCanBeConvertedToTarget = freqs[r] - (l == 0?0: freqs[l-1]);
            int opsNeeded = numsThatCanBeConvertedToTarget - targetFreq;
            int maxPossibleFreqOfTarget = targetFreq + Math.min(opsNeeded, numOperations);

            maxFreq = Math.max(maxFreq, maxPossibleFreqOfTarget);
        }

        return maxFreq;
    }
}
