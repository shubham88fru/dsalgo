package lc_potd;

//@link - https://leetcode.com/problems/find-k-th-smallest-pair-distance/
//@check - https://www.youtube.com/watch?v=hx8Ssz_3XSs&t=1855s
public class FindKthSmallestPairDistance {
    public int smallestDistancePair(int[] nums, int k) {
        //1. Brute force will give TLE.
        //2. Soln using heaps will give TLE too.

        //mik's approach. written by me.

        //find min and max to create an array.
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int num: nums) {
            if (num < min) min = num;
            if (num > max) max = num;
        }

        //create an array that will have
        //a slot for each for each possible difference
        //between each pair of nums. From 0 till max-min+1;
        //Keep incrementing the count of the slot
        //if we encounter the diff.
        int[] diffs = new int[max-min+1];
        for (int i=0; i<nums.length-1; i++) {
            for (int j=i+1; j<nums.length; j++) {
                diffs[Math.abs(nums[j]-nums[i])] += 1;
            }
        }

        //iterate through the diffs array.
        //as soon as we encountered k diffs,
        //we know that we've got the answer.
        int cnt = 0;
        for (int i=0; i<diffs.length; i++) {
            if (cnt + diffs[i] >= k) return i;
            cnt += diffs[i];
        }

        return -1;
    }
}
