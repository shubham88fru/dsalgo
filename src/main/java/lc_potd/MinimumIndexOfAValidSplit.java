package lc_potd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@link - https://leetcode.com/problems/minimum-index-of-a-valid-split/description/
//@check - https://www.youtube.com/watch?v=MtZHKHOHmAs&t=1802s&ab_channel=codestorywithMIK
public class MinimumIndexOfAValidSplit {
    public int minimumIndex(List<Integer> nums) {
        return brute(nums);
    }

    /*
    * My soln. This is necessarily brute force
    * because TC is O(N) only.
    *
    * However, SC is also O(N)
    * Mik also showed a second approach using moore's voting algorithm
    * which removes the need of extra space. It can be a followup in an
    * interview.
    * */
    private int brute(List<Integer> nums) {
        int n = nums.size();

        Map<Integer, Integer> freq = new HashMap<>();

        int maxFreq = -1;
        int dom = -1;
        for (int num: nums) {
            freq.put(num, freq.getOrDefault(num, 0)+1);
            if (freq.get(num) > maxFreq) {
                maxFreq = freq.get(num);
                dom = num;
            }
        }

        int lc = 0;
        for (int i=0; i<n; i++) {
            if (nums.get(i) == dom) {
                lc += 1;
                int rc = maxFreq - lc;

                if ((lc * 2 > i+1) && (rc * 2 > n-i-1)) return i;

            }
        }

        return -1;
    }
}
