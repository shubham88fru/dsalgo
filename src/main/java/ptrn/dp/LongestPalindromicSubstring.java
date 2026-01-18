package ptrn.dp;

//@link - https://leetcode.com/problems/longest-palindromic-substring/
//@check - https://www.youtube.com/watch?v=n_kL8BkURVA&t=1009s&ab_channel=codestorywithMIK
//       - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5590882570207232
public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        // return brute(s);
        // return recursionWithMemoization(s);
        return bottomUpDp(s);
    }

    private String bottomUpDp(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];

        for (int i=0; i<n; i++) dp[i][i] = true;

        int maxLen = 1;
        int maxi = 0, maxj = 0;
        for (int L=2; L<=n; L++) {
            for (int i=0; i<=n-L; i++) {
                int j = i+L-1;
                if (s.charAt(i) == s.charAt(j)) {
                    if (L == 2) dp[i][j] = true;
                    else dp[i][j] = dp[i+1][j-1];
                }

                if (dp[i][j]) {
                    maxLen = L;
                    maxi = i;
                    maxj = j;
                }
            }
        }

        return s.substring(maxi, maxj+1);
    }
    /**
     Note how this is a variant of dp (albeit top-down)
     which doesn't come into the knap sack (pick/notPick)
     type of problem.

     Caching and all everything remains same, just that
     we don't have choices, all we have is repeating
     subproblem when checking whether a substring is
     a palindrome or not.

     The caching/memoization is actually done
     in the recursive palindrome check call.
     */
    private String recursionWithMemoization(String s) {
        Boolean[][] cache = new Boolean[s.length()][s.length()];
        int maxLen = Integer.MIN_VALUE;
        int start = 0;
        for (int i=0; i<s.length(); i++) {
            for (int j=i; j<s.length(); j++) {
                if (palindrome(s, i, j, cache)) {
                    if (j-i+1>maxLen) {
                        maxLen = j-i+1;
                        start = i;
                    }
                }
            }
        }

        return s.substring(start, start+maxLen);
    }

    //recursive algorithm to check for palindrome.
    private boolean palindrome(String s, int i, int j, Boolean[][] cache/*Map<String, Boolean> cache*/) {
        if (i >= j) return true;

        if (cache[i][j] != null) return cache[i][j];

        if (s.charAt(i) == s.charAt(j)) {
            boolean res = palindrome(s, i+1, j-1, cache);
            cache[i][j] = res;
            return cache[i][j];
        }

        cache[i][j] = false;
        return cache[i][j];
    }

    //TLE.
    private String brute(String s) {
        int m = s.length()-1;

        while (m >= 1) {
            for (int i=0; i<s.length()-m; i++) {
                String str = s.substring(i, i+m+1);
                if (str.equals(new StringBuffer(str).reverse().toString())) return str;
            }
            m -= 1;
        }

        return s.substring(0, 1);
    }
}

