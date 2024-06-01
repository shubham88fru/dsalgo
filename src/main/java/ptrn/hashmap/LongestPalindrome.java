package ptrn.hashmap;

import java.util.HashMap;
import java.util.Map;


//@link - https://leetcode.com/problems/longest-palindrome/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5995611162935296
public class LongestPalindrome {
    public int longestPalindrome(String s) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char ch: s.toCharArray()) {
            freq.put(ch, freq.getOrDefault(ch, 0)+1);
        }

        int maxLen = 0;
        boolean hasOddEl = false;
        for (Map.Entry<Character, Integer> entry: freq.entrySet()) {
            //If freq is even, all chars can be distributed on two sides.
            if (entry.getValue()%2 == 0) maxLen += entry.getValue();
            else {
                //If freq is odd, all chars except one can be distributed on
                //the two sides.
                maxLen += (entry.getValue()-1);
                hasOddEl = true;
            }
        }

        //If the string has an odd freq char, then we can include it in the middle,
        //while distributing the rest on two sides.
        return hasOddEl ? (maxLen + 1) : maxLen;
    }
}
