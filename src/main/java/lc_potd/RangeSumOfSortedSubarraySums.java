package lc_potd;

import java.util.PriorityQueue;

//@link - https://leetcode.com/problems/range-sum-of-sorted-subarray-sums
//@check - https://www.youtube.com/watch?v=oMHO5iti5_c&t=1724s&ab_channel=codestorywithMIK
public class RangeSumOfSortedSubarraySums {
    public int rangeSum(int[] nums, int n, int left, int right) {
        // return brute(nums, n, left, right);
        return better(nums, n, left, right);
    }

    //better - mik's sol.
    private int better(int[] nums, int n, int left, int right) {
        /**
         Intuition is that we can use Heap to find subarray sums of
         an array in increasing/decreasing order.

         NOTE: Not 100% sure if this approach will work
         if arrays can contain -ves also.
         */
        PriorityQueue<int[]> pq = new PriorityQueue<>((a1, a2) -> a1[0]-a2[0]);
        for (int i=0; i<nums.length; i++) {
            //Insert each element of array and its index as a starting point.
            //Remember, each individual element of the array is also a valid
            //subarray.
            pq.add(new int[]{nums[i], i});
        }

        int i = 0;
        long rsum = 0;
        while (!pq.isEmpty()) {
            int[] pair = pq.remove();
            int currSubSum = pair[0];
            int currSubSumEndIdx = pair[1]; //end index of current subarray.

            if (currSubSumEndIdx < nums.length-1) {
                pq.add(new int[]{ currSubSum + nums[currSubSumEndIdx+1], currSubSumEndIdx+1 });
            }

            i += 1;
            if (i >= left && i<= right) {
                rsum += currSubSum;
            }

            if (i == right) break;
        }

        return (int)(rsum%1000000007);

    }

    //brute - my sol
    private int brute(int[] nums, int n, int left, int right) {
        PriorityQueue<Long> pq = new PriorityQueue<>();

        for (int i=0; i<nums.length; i++) {
            long subsum = 0;
            for (int j=i; j<nums.length; j++) {
                subsum += nums[j];
                pq.add(subsum);
            }
        }

        int i = 0;
        long rsum = 0;
        while (!pq.isEmpty()) {
            long nm = pq.remove();
            i += 1;
            if (i >= left && i<= right) {
                rsum += nm;
            }
            if (i == right) break;
        }

        return (int)(rsum%1000000007);
    }
}
