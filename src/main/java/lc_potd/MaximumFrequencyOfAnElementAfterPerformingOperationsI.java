package lc_potd;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/maximum-frequency-of-an-element-after-performing-operations-i/description/?
//@check - https://www.youtube.com/watch?v=CKha8fqTwBA
public class MaximumFrequencyOfAnElementAfterPerformingOperationsI {
    public int maxFrequency(int[] nums, int k, int numOperations) {
        return mikssol(nums, k, numOperations);
    }

    /**
     Approach 2 -
     Coded by mea based on mik's explanation for diff
     array technique.
     https://www.youtube.com/watch?v=BaEebscdBJM

     Diff array technique in short -
     1. diff[l] += 1; diff[r+1] -= 1;
     2. cummulative sum.

     The difference between this and Appproach 1 is
     ofcourse that this approach uses diff array technique
     but also that in approach 1 we were viewing how many
     nums can be converted to target, but in approach 2
     its slightly different way of looking at it.
     */
    private int miksDiffArrayTechnique(int[] nums, int k, int numOperations) {
        int max = Integer.MIN_VALUE;
        for (int num: nums) max = Math.max(num+k, max); //target can be from 0..max+k

        int[] diff = new int[max+2];
        Map<Integer, Integer> ogFreq = new HashMap<>();

        for (int num: nums) {
            ogFreq.put(num, ogFreq.getOrDefault(num, 0)+1);

            int l = Math.max(0, num-k);
            int r = Math.min(max, num+k);

            diff[l] += 1;
            diff[r+1] -= 1;
        }

        int maxFreq = 0;
        for (int target=0; target<=max; target++) {
            diff[target] += (target == 0 ? 0: diff[target-1]); //how many times this target can be made.

            int targetFreq = ogFreq.getOrDefault(target, 0); //freq of target in og array.
            int numConversionsNeeded = diff[target] - targetFreq;
            int maxTargetFreqPossible = targetFreq + Math.min(numOperations, numConversionsNeeded);

            maxFreq = Math.max(maxFreq, maxTargetFreqPossible);
        }

        return maxFreq;
    }

    /**
     * Approach 1 -
     Coded by me completely based on mik's explanation.
     Couldn't even come up with a brute force soln :(

     The main idea behind this problem is that given a
     number `target`, any number in the range [target-k, target+k]
     can be converted to target.
     */
    private int mikssol(int[] nums, int k, int numOperations) {
        int max = Integer.MIN_VALUE;
        for (int num: nums) max = Math.max(num+k, max); //target can be from 0..max+k

        int[] freqs = new int[max+1];
        for (int num: nums) freqs[num] += 1;

        //cummulative freq.
        //Optimization - otherwise, for each target,
        //we'll need to iterate the array to count
        //how many nums lie between [l, r]
        for (int i=1; i<freqs.length; i++) freqs[i] += freqs[i-1];

        int maxFreq = 0;
        for (int target=0; target<=max; target++) {
            int l = Math.max(0, target-k);
            int r = Math.min(max, target+k);

            int totalThatCanBeConvertedToTarget = freqs[r] - (l == 0 ? 0: freqs[l-1]);
            int targetFreq = freqs[target] - (target == 0? 0: freqs[target-1]);
            int requiredOperations = totalThatCanBeConvertedToTarget - targetFreq;
            int maxFreqOfTargetAfterConversions = targetFreq + Math.min(numOperations, requiredOperations);

            maxFreq = Math.max(maxFreq, maxFreqOfTargetAfterConversions);
        }

        return maxFreq;
    }
}
