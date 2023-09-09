package strvr.recursion;

import java.util.ArrayList;
import java.util.List;

//@link - https://leetcode.com/problems/palindrome-partitioning/description/
//@strvr - https://takeuforward.org/data-structure/palindrome-partitioning/
public class PalindromePartitioning {
    public List<List<String>> partition(String s) {
        //will store all result here.
        List<List<String>> result = new ArrayList<>();
        generateAllCombinations(s, 0, s.length()-1, new ArrayList<>(), result);

        //return the populated result.
        return result;
    }

    private void generateAllCombinations(String s, int start, int end, ArrayList<String> currentConfiguration, List<List<String>> result) {

        //base case. no more substrings possible.
        if (start > end) {
            result.add(new ArrayList<>(currentConfiguration));
            return;
        }

        //for each substring, we'll try to make a
        //cut (new substring) at each index. That too,
        //only if left substring is a valid palindrome.
        for (int i=start; i <= end; i++) {
            String sub = s.substring(start, i+1);
            if (isPalindrome(sub)) {
                //if left part is a palindrome, add it to
                //list and recursively call for right part (sub string)
                currentConfiguration.add(sub);
                generateAllCombinations(s, i+1, end, currentConfiguration, result);

                //once done, backtrack to reverset the change done to `currentConfiguration`
                //to original state.
                currentConfiguration.remove(currentConfiguration.size()-1);
            }
        }
    }

    private boolean isPalindrome(String s) {
        int start = 0;
        int end = s.length()-1;
        while (start <= end) {
            if (s.charAt(start) != s.charAt(end)) return false;
            start++;
            end--;
        }

        return true;
    }
}
