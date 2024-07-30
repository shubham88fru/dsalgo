package lc_potd;

//@link - https://leetcode.com/problems/minimum-deletions-to-make-string-balanced/description/
public class MinimumDeletionsToMakeStringBalanced {
    public int minimumDeletions(String s) {

        /**
         * On next encounter, retry or check a video.
         */
        /////////NOT MY SOLN////////////
        int n = s.length();
        int[] f = new int[n + 1];
        int b = 0;
        for (int i = 1; i <= n; ++i) {
            if (s.charAt(i - 1) == 'b') {
                f[i] = f[i - 1];
                ++b;
            } else {
                f[i] = Math.min(f[i - 1] + 1, b);
            }
        }
        return f[n];
        /////////NOT MY SOLN////////////
    }


    //My top-dwn dp soln. Gives TLE.
    private int mySoln(String s) {
        return solve(s, 0, 0, 0, 0, new Integer[99][99][s.length()][s.length()]);
    }

    private int solve(String orig, int last, int slast, int idx, int bc, Integer[][][][] memo) {

        // if (bc != 0 && curr.charAt(curr.length()-1) == 'a') return 1000001;
        if (bc != 0 && last == 97) return 1000001;
        if (idx >= orig.length()) return 0;

        // String key = curr + "_" + idx + "_" + bc;
        // if (memo.containsKey(key)) return memo.get(key);
        if (memo[last][slast][idx][bc] != null) return memo[last][slast][idx][bc];

        int ch = orig.charAt(idx);
        int select = solve(orig, ch, last, idx + 1, (ch == 'b' ? bc + 1 : bc), memo);

        int delete = 1 + solve(orig, last, slast, idx + 1, bc, memo);

        // memo.put(key, Math.min(select, delete));
        // return memo.get(key);
        memo[last][slast][idx][bc] = Math.min(select, delete);
        return memo[last][slast][idx][bc];
    }
}
