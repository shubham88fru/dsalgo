package swd.hashmap;

import java.util.HashMap;
import java.util.Map;

//@link - https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/hashmap-and-heaps/longest-subarray-with-equal-number-of-0s-1s-and-2s-official/ojquestion
public class LongestSubArrayWithEqualNumOf0s1sAnd2s {
    public static int solution(int[] arr) {
        // write your code here
        int z0 = 0;
        int z1 = 0;
        int z2 =0;
        Map<String, Integer> memo = new HashMap<>();
        memo.put("0#0", -1); //assuming 0#0 at -1 index.

        int ans = 0;

        for (int i=0; i<arr.length; i++) {
            if (arr[i] == 0) z0++;
            else if (arr[i] == 1) z1++;
            else z2++;

            String key = (z1-z0) + "#" + (z2-z1);
            if (memo.containsKey(key)) {
                ans = Math.max(ans, i-memo.get(key));
            } else memo.put(key, i); //Note: Putting index here. Not frequency.
        }
        return ans;
    }
}
