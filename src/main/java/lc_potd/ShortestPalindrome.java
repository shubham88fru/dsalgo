package lc_potd;

//@link - https://leetcode.com/problems/shortest-palindrome/
//@check - https://www.youtube.com/watch?v=5DACQK9kud0&t=469s&ab_channel=codestorywithMIK
public class ShortestPalindrome {
    public String shortestPalindrome(String s) {
        /**
         One of the easiest way to solve this problem is
         basically seeing this problem as finding the longest
         palindromic substring from the start (coz we have insert only at the start).
         Basically, from index 0, find the longest palindromic substring
         and the rest of the string is basically the part that is a creating a blocker.
         So, take that rest of the string, reverse it and append it to the start of the og string.
         */

        //Coded by me but apprach by mik.
        StringBuilder sb = new StringBuilder(s); //aacecaaa

        //take reverse, makes it easier to find the bad string.
        StringBuilder sb2 = new StringBuilder(s);
        sb2.reverse(); //aaacecaa

        int s1 = 0;
        int s2 = 0;
        while (!sb.substring(0, sb.length()-s1).toString().equals(sb2.substring(s2))) {
            s1 += 1;
            s2 += 1;
        }

        return sb2.substring(0, s2).toString() + s;

    }
}
