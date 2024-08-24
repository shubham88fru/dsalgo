package lc_potd;

import java.util.ArrayList;
import java.util.List;

//@link - https://leetcode.com/problems/find-the-closest-palindrome/description/
//@check - https://www.youtube.com/watch?v=PTVYvSd9gUw
public class FindTheClosestPalindrome {
    /**
     * Almost a copypasta. Didn't get most of it.
     * Mik had not uploaded a soln, so shamelessly copied from a different channel.
     *
     */
    public String nearestPalindromic(String n) {
        int index = n.length()/2;
        boolean even = n.length()%2==0;
        if (even) index -= 1;
        long leftPart = Long.parseLong(n.substring(0, index+1));

        //5 cases.
        List<Long> cases = new ArrayList<>();

        //case 1. converting ending digits to starting digits.
        cases.add(getPalindrome(leftPart, even));

        //case 2. incrementing middle.
        cases.add(getPalindrome(leftPart+1, even));

        //case 3. decrementing middle.
        cases.add(getPalindrome(leftPart-1, even));

        //case 4. reduce digits by 1.
        cases.add((long)Math.pow(10, n.length()-1)-1);

        //case 5. increase digits by 1.
        cases.add((long)Math.pow(10, n.length())+1);

        long result = 0;
        long diff = Integer.MAX_VALUE;
        long num = Long.parseLong(n);

        for (long c: cases) {
            if (c == num) continue;
            long curr = Math.abs(num-c);
            if (diff > curr) {
                result = c;
                diff = curr;
            } else if (diff == curr) {
                result = Math.min(result, c);
            }
        }

        return String.valueOf(result);
    }

    private long getPalindrome(long leftPart, boolean even) {
        long result = leftPart;
        if (!even) leftPart/=10;

        //reverse the nums before mid and keep appending.
        while (leftPart>0) {
            result = (result*10)+(leftPart%10);
            leftPart /= 10;
        }

        return result;
    }
}
