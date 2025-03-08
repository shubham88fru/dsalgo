package ptrn.dp;

import java.util.Map;

//@link - https://leetcode.com/problems/interleaving-string/description/
//@check - https://www.youtube.com/watch?v=CfzP4oXxZTI&t=608s&ab_channel=codestorywithMIK
public class InterleavingString {
    public boolean isInterleave(String s1, String s2, String s3) {
        return pass1(s1, s2, s3);
    }

    private boolean pass1(String s1, String s2, String s3) {
        int n1 = s1.length();
        int n2 = s2.length();
        int n3 = s3.length();
        if (n1 + n2 != n3) return false;

        // Map<String, Boolean> memo = new HashMap<>();
        // return dp(s1, s2, "", s3, memo) || dp(s2, s1, "", s3, memo);

        Boolean[][] dp = new Boolean[n1+1][n2+1];
        return dp2(s1, s2, s3, 0, 0, 0, dp);
    }

    /*
    * Took a small but important hint from mik.
    * The hint changed the entire approach.
    *  */
    private boolean dp2(String s1, String s2, String s3, int i, int j, int k, Boolean[][] dp) {
        /*
            3d dp.
        */
        // if (k >= (s1.length() + s2.length())) return true;

        /*
            2d dp.
        */
        if (i + j >= (s1.length() + s2.length())) return true;

        if (dp[i][j] != null) return dp[i][j];

        char ch3 = s3.charAt(k);

        boolean one = false;
        if (i < s1.length() && s1.charAt(i) == ch3) {
            one = dp2(s1, s2, s3, i+1, j, k+1, dp);
        }

        boolean two = false;
        if (j < s2.length() && s2.charAt(j) == ch3) {
            two = dp2(s1, s2, s3, i, j+1, k+1, dp);
        }

        dp[i][j] = (one || two);
        return dp[i][j];
    }

    /**
     My approach. Gives TLE.
     Probably because of 3d dp.
     did not use the fact that we already know which character
     we need at an index in s3.
     */
    private boolean dp(String s1, String s2, String s4, String s3, Map<String, Boolean> memo) {
        if (s1.length() == 0) s4 += s2;
        if (s2.length() == 0) s4 += s1;

        if (s4.equals(s3)) return true;

        String key = s1+"_"+s2+"_"+s4;
        if (memo.containsKey(key)) return memo.get(key);

        for (int i=0; i<s1.length(); i++) {
            String sub1 = s1.substring(0, i+1);

            for (int j=0; j<s2.length(); j++) {
                String sub2 = s2.substring(0, j+1);
                if (dp(s1.substring(i+1), s2.substring(j+1), s4+sub1+sub2, s3, memo)) {
                    memo.put(key, true);
                    return true;
                }
            }
        }

        memo.put(key, false);

        return false;
    }
}
