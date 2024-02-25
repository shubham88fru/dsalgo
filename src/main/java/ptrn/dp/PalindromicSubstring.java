package ptrn.dp;

//@link - https://leetcode.com/problems/palindromic-substrings/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/4796583973224448
public class PalindromicSubstring {
    public int countSubstrings(String s) {
        //return brute(s);
        return bottomupdp(s);
    }

    //1) Edctv soln.
    //If confused, check @check. Edctv explaination is good.
    private int bottomupdp(String s) {
        int n = s.length();
        int count = 0;
        //In this dp array, valut at position (i, j)
        //represents whether the susbtring s[i..j] is
        //a palindrome or now. Note that only the upper
        //of this matrix (j >= i) is actually meaningful.
        //Lower half is kinda meaningless, because it doesn't
        //represent an actual substring.
        boolean[][] dp = new boolean[n][n];

        //Diagonal positions of the dp array
        //represent the substrings of length 1
        //(coz i and j are same)
        for (int i=0; i<n; i++) {
            dp[i][i] = true;
            count += 1;
        }

        //After accounting for all substrings of
        //length one, we do the same for all substrings
        //of length two.
        for (int i=0; i<s.length()-1; i++) {
            dp[i][i+1] = (s.charAt(i) == s.charAt(i+1));
            //if the substring (of length 2) is a palindrome
            //add count.
            count += (dp[i][i+1] ? 1: 0);
        }

        //Now, for all susbtrings of higher length (i.e. more than 2),
        //starting length 3 onwards, we will continously check if they are
        //palindrome and update our dp matrix. Note that to do the check, we'll
        //just need to effectively compare the first and last char coz the answer
        //for the inner string (from second to second last) would already have been
        //stored in the dp matrix from the previous iteration.
        for (int length = 3; length <= s.length(); length++) {
            for (int i=0, j=length-1; j<s.length(); i++, j++) {
                //compare first and last char, and get the answer for
                //the inner string fromthe dp matrix. If inner is a palindrome,
                //and first and last char are same as well, then the entire string
                //is also a palindrome.
                dp[i][j] = (s.charAt(i) == s.charAt(j)) && dp[i+1][j-1];
                count += (dp[i][j] ? 1: 0);
            }
        }

        return count;
    }

    //2) My soln - brute force.
    //Notice how simple it is to generate substrings.
    //Don't confuse with generating permutations or maybe subsequences.
    private int brute(String s) {
        int count = 0;
        //generates all substrings and checks
        //if palindrome for each.
        for (int i=0; i<s.length(); i++) {
            for (int j=i; j<s.length(); j++) {
                if (isPalindrome(s, i, j)) count += 1;
            }
        }

        return count;
    }

    private boolean isPalindrome(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) return false;
            l += 1;
            r -= 1;
        }

        return true;
    }
}
