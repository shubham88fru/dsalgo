package lc_potd;

import java.util.*;

//@link - https://leetcode.com/problems/longest-square-streak-in-an-array/
//@check - https://www.youtube.com/watch?v=Xpj6GQgP7og&ab_channel=codestorywithMIK
public class LongestStreakInAnArray {
    public int longestSquareStreak(int[] nums) {
        Arrays.sort(nums);


        // return trydp(nums);
        // return tryBinarySearch(nums);
        // return usingHashMap(nums);
        return usingSet(nums);
    }

    //Inspired by mik's explanation.
    private int usingSet(int[] nums) {
        Set<Long> st = new HashSet<>();
        for (int i=0; i<nums.length; i++) {
            st.add((long)nums[i]);
        }

        int maxLen = 0;
        for (int i=0; i<nums.length; i++) {
            int len = 1;
            long next = (long)nums[i]*nums[i];
            while (st.contains(next)) {
                if (next > Math.pow(10, 5)) break;

                next = next*next;
                len += 1;
            }

            maxLen = Math.max(len, maxLen);
        }

        return (maxLen == 1 ? -1: maxLen);
    }

    //Inspired by mik's explanation.
    //Feel very stupid that I couldn't comeup with this myself.
    private int usingHashMap(int[] nums) {
        Map<Double, Integer> mp = new HashMap<>();

        int maxLen = 0;
        for (int i=0; i<nums.length; i++) {
            double sqrt = Math.sqrt(nums[i]);

            mp.put((double)nums[i], mp.getOrDefault(sqrt, 0)+1);
            maxLen = Math.max(maxLen, mp.getOrDefault(sqrt, 0)+1);
        }

        return maxLen == 1 ? -1: maxLen;
    }

    //My sol using binary search.
    //works but TC is not that great.
    private int tryBinarySearch(int[] nums) {
        int maxLen = 0;
        for (int i=0; i<nums.length; i++) {
            int next = nums[i]*nums[i];
            int len = 1;
            while (bs(nums, next, i+1) != -1) {
                next = next*next;
                len += 1;
            }
            maxLen = Math.max(len, maxLen);
        }

        return (maxLen == 1 ? -1: maxLen);
    }

    private int bs(int[] nums, int target, int start) {
        int l = start;
        int r = nums.length-1;

        while (l <= r) {
            int mid = l + (r-l)/2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] > target) {
                r = mid -1;
            } else l = mid + 1;
        }

        return -1;
    }

    private int trydp(int[] nums) {
        Map<String, Integer> memo = new HashMap<>();

        int lss = dp(nums, 0, -1, memo);
        return (lss <= 1 ? -1: lss);
    }

    //TLE.
    private int dp(int[] nums, int curr, int prev, Map<String, Integer> memo) {
        if (curr >= nums.length) return 0;

        String key = curr + "_" + prev;
        if (memo.containsKey(key)) return memo.get(key);

        int choose = 0;
        if ((prev == -1) || ((nums[curr]*1.0)/prev) == (1.0*prev)) {
            choose = 1 + dp(nums, curr+1, nums[curr], memo);
        }

        int notChoose = dp(nums, curr+1, prev, memo);

        memo.put(key,  Math.max(choose, notChoose));
        return memo.get(key);

    }
}
