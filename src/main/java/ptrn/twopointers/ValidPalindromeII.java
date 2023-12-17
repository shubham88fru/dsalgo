package ptrn.twopointers;

//@link - https://leetcode.com/problems/valid-palindrome-ii/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6726901218672640
public class ValidPalindromeII {
    //Maybe not the best solution though.
    public boolean validPalindrome(String s) {
        int n = s.length();
        int start = 0;
        int end = s.length() - 1;

        while (start <= end) {
            if (s.charAt(start) != s.charAt(end)) {
                //try skipping at `start`
                //and see if remaining string is palindrome.
                if (checkPalindrome(s.substring(start+1, end+1))) return true;
                else if (checkPalindrome(s.substring(start, end))) return true;
                else return false;
            }
            start += 1;
            end -= 1;
        }

        return true;

    }

    private boolean checkPalindrome(String s) {
        int n = s.length();
        int start = 0;
        int end = s.length() - 1;

        while (start <= end) {
            if (s.charAt(start) != s.charAt(end)) return false;
            start += 1;
            end -= 1;
        }

        return true;
    }
}
