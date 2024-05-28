package lc_potd;

//@link - https://leetcode.com/problems/get-equal-substrings-within-budget/
public class GetEqualSubstringsWithinBudget {
    public int equalSubstring(String s, String t, int maxCost) {
        int l = 0;
        int r = 0;
        int cost = 0;
        int maxLen = 0;
        while (r < s.length()) {
            int additional = Math.abs(s.charAt(r)-t.charAt(r));
            if (cost+additional <= maxCost) {
                cost += additional;
                maxLen = Math.max(maxLen, r-l+1);
                r += 1;
            } else {
                cost -= Math.abs(s.charAt(l)-t.charAt(l));
                l += 1;
            }
        }

        return maxLen;
    }
}
