package strvr.recursion;

import java.util.ArrayList;
import java.util.List;

//@link - https://leetcode.com/problems/palindrome-partitioning/description/
//@strvr - https://takeuforward.org/data-structure/palindrome-partitioning/
public class PalindromePartitioning {
    public List<List<String>> partition(String s) {
        // return revise(s);
        return approach2(s);
    }

    private List<List<String>> approach2(String s) {
        List<List<String>> ans = new ArrayList<>();
        int n = s.length();

        //Pre-fill the palindrome array.
        //for any substring i..j, this array
        //will tell if that substring is a palindrome
        //in O(1)
        boolean[][] palindrome = new boolean[n][n];
        for (int i=0; i<n; i++) {
            //all single length
            //substrings are palindrome.
            palindrome[i][i] = true;
        }

        for (int L=2; L<=n; L++) { //for each possible length
            for (int i=0; i<n-L+1; i++) { //check from each index
                int j = i+L-1; //end index.
                if (s.charAt(i) == s.charAt(j)) {
                    if (L == 2) {
                        palindrome[i][j] = true;
                    } else {
                        palindrome[i][j] = palindrome[i+1][j-1]; //ans will depend on inner string.
                    }
                }
            }
        }

        backtrack2(s, 0, palindrome, ans, new ArrayList<>());
        return ans;
    }

    private void backtrack2(String s, int i, boolean[][] palindrome, List<List<String>> ans, List<String> curr) {
        if (i >= s.length()) {
            ans.add(new ArrayList<>(curr));
            return;
        }

        for (int j=i; j<s.length(); j++) {
            String leftSub = s.substring(i, j+1);
            if (palindrome[i][j]) {
                curr.add(leftSub);
                backtrack2(s, j+1, palindrome, ans, curr);
                curr.remove(curr.size()-1);
            }
        }
    }

    private List<List<String>> revise(String s) {
        List<List<String>> ans = new ArrayList<>();

        backtrack(s, ans, new ArrayList<>());
        return ans;
    }

    private void backtrack(String s, List<List<String>> ans, List<String> curr) {
        if (s.length() == 0) {
            ans.add(new ArrayList<>(curr));
            return;
        }

        for (int i=0; i<s.length(); i++) {
            String leftSub = s.substring(0, i+1);
            if (isPalindrome(leftSub)) {
                curr.add(leftSub);
                backtrack(s.substring(i+1), ans, curr);
                curr.remove(curr.size()-1);
            }
        }
    }



    private boolean isPalindrome(String s) {
        int l = 0;
        int r = s.length()-1;

        while (l <= r) {
            if (s.charAt(l) != s.charAt(r)) return false;
            l += 1;
            r -= 1;
        }

        return true;
    }
}
