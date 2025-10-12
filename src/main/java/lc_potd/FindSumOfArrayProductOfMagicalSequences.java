package lc_potd;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/find-sum-of-array-product-of-magical-sequences/?
//@check - https://www.youtube.com/watch?v=tC698np8o6s&t=246s
public class FindSumOfArrayProductOfMagicalSequences {
    public int magicalSum(int m, int k, int[] nums) {
         return pass1(m, k, nums);
    }
    /**
     Coded by me based on mik's explanation.
     Give TLE.

     Mik showed the optimal approach but its
     too complicated.
     */
    private int pass1(int m, int k, int[] nums) {

        return (int)topdown(m, k, nums, 0, 0, new HashMap<String, Long>());
    }

    private long topdown(int m, int k, int[] nums, long sum, long count, Map<String, Long> memo) {
        if (count == m) {
            if (Long.bitCount(sum) == k) return 1;
            return 0;
        }

        String key = count + "_" + String.valueOf(sum);
        if (memo.containsKey(key)) return memo.get(key);

        long totalSum = 0;
        for (int i=0; i<nums.length; i++) {
            long sumOfProducts = ((long)nums[i] * (long)(topdown(m, k, nums, sum+(long)(1L<<i), count+1, memo)))%1000000007;
            totalSum = (totalSum + sumOfProducts)%1000000007;
        }

        /**
         * Note: returning totalSum, its a bit
         * tricky to visualize how the pgrm is working
         * but try with a small example, u'll understand.
         */
        memo.put(key, totalSum);
        return memo.get(key);
    }
}
