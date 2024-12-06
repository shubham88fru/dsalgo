package lc_potd;

import java.util.HashSet;
import java.util.Set;

//@link - https://leetcode.com/problems/maximum-number-of-integers-to-choose-from-a-range-i/
public class MaximumNumberOfIntegersToChooseFromARangeI {
    public int maxCount(int[] banned, int n, int maxSum) {
        return mysol(banned, n, maxSum);
    }

    private int mysol(int[] banned, int n, int maxSum) {
        Set<Integer> st = new HashSet<>();
        for (int ban: banned) st.add(ban);


        int count = 0;
        int sum = 0;
        for (int i=1; i<=n; i++) {
            if (!st.contains(i) && (sum + i) <= maxSum) {
                count += 1;
                sum += i;
            }

            if (sum > maxSum) return count;
        }

        return count;
    }
}
