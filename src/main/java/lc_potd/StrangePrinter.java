package lc_potd;

//@link - https://leetcode.com/problems/strange-printer/
//@check - https://www.youtube.com/watch?v=pV3arpA0TzY
public class StrangePrinter {
    /**
     @copypasta from Mik.
     barely understood.
     */
    public int strangePrinter(String s) {
        Integer[][] memo = new Integer[s.length()+1][s.length()+1];
        return solve(0, s.length()-1, s, memo);
    }

    private int solve(int l, int r, String s, Integer[][] memo) {
        if (l == r) return 1;
        if (l > r) return 0;

        if (memo[l][r] != null) return memo[l][r];

        int i = l + 1;
        while (i <= r && (s.charAt(i) == s.charAt(l))) i += 1;
        if (i == r +1) return 1;

        int basic = 1 + solve(i, r, s, memo);

        int lalach = Integer.MAX_VALUE;
        for (int j=i; j<= r; j++) {
            if (s.charAt(j) == s.charAt(l)) {
                int ans = solve(i, j-1, s, memo) + solve(j, r, s, memo);
                lalach = Math.min(lalach, ans);
            }
        }

        memo[l][r] = Math.min(basic, lalach);
        return memo[l][r];
    }
}
