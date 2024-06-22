package lc_potd;

import java.util.ArrayList;
import java.util.List;

//@link - https://leetcode.com/problems/count-number-of-nice-subarrays/description/
//@check - https://www.youtube.com/watch?v=dhu5_v2iY8E&ab_channel=AryanMittal
public class CountNumberOfNiceSubarrays {
    public int numberOfSubarrays(int[] nums, int k) {
        return sol1(nums, k);
    }

    //0) @check for a prefix sum soln.

    //1) Sliding window - My soln.
    private int sol1(int[] nums, int k) {
        int n = nums.length;
        int l = 0;
        int r = 0;
        int subarr = 0;

        //keep track of all the indexes of odd nums (in order).
        List<Integer> orderedOddIndexes = new ArrayList<>();
        while (r < n) {

            //If num is odd, record the index.
            if (nums[r]%2 != 0) {
                orderedOddIndexes.add(r);
            }

            //If we see more than `k` odd nums,
            //shrink the window.
            if (orderedOddIndexes.size() > k) {
                int firstOddIdx = orderedOddIndexes.get(0);
                orderedOddIndexes.remove(0);
                l = firstOddIdx + 1; //shrink directly till the first occurrence of odd num.
            }

            //If we've seen k odds, calculate the subarray.
            if (orderedOddIndexes.size() == k) {
                int firstOddIdx = orderedOddIndexes.get(0);
                subarr += (firstOddIdx - l + 1);
            }

            r += 1;
        }

        return subarr;
    }


    //2) Brute forceish. TLE.
    private int sol2(int[] nums, int k) {
        int n = nums.length;
        int l = 0;
        int r = 0;

        int subarr = 0;
        while (l < n) {
            int oddCount = 0;
            while (r < n) {
                if (nums[r]%2 != 0) {
                    oddCount += 1;
                    if (oddCount > k) break;

                }
                if (oddCount == k) {
                    subarr += 1;
                }
                r += 1;
            }
            l += 1;
            r = l;
        }

        return subarr;
    }
}
