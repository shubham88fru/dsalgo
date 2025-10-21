package lc_potd;

//@link - https://leetcode.com/problems/maximum-frequency-of-an-element-after-performing-operations-i/description/?
//@check - https://www.youtube.com/watch?v=CKha8fqTwBA
public class MaximumFrequencyOfAnElementAfterPerformingOperationsI {
    public int maxFrequency(int[] nums, int k, int numOperations) {
        return mikssol(nums, k, numOperations);
    }

    /**
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
