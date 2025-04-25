package lc_potd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@link - https://leetcode.com/problems/count-of-interesting-subarrays/
//@check - https://www.youtube.com/watch?v=MTQWe8qHcz0&ab_channel=codestorywithMIK
public class CountOfInterestingSubarrays {
    /*
        Direct copypasta of mik's soln.
        Had a lot of confusing modulo properties
        which made this problem very irritating.
    */
    public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        int n = nums.size();
        Map<Integer, Long> mp = new HashMap<>(); // remainder -> count
        long sum = 0;
        long result = 0;

        mp.put(0, 1L);

        for (int i = 0; i < n; i++) {
            if (nums.get(i) % modulo == k) {
                sum += 1;
            }

            int r1 = (int)(sum % modulo);
            int r2 = (r1 - k + modulo) % modulo;

            result += mp.getOrDefault(r2, 0L);
            mp.put(r1, mp.getOrDefault(r1, 0L) + 1);
        }

        return result;
    }
}
