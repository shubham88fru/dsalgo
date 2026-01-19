package ptrn.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//@link - https://leetcode.com/problems/restore-ip-addresses/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/4629257998565376
public class RestoreIPAddresses {


    public List<String> restoreIpAddresses(String str) {
        // return edctvSol(str);
        return revise(str);
    }

    /**
     * Following is my backtracking soln.
     * Has slight corner cases that I could
     * only handle after trying out test cases,
     * but is extremely simpler and intuitive
     * than edctv's soln. Maybe it can
     * be made even simpler.
     */
    private List<String> revise(String s) {

        List<String> ans = new ArrayList<>();
        backtrack(new StringBuffer(s), 0, 3, ans);

        return ans;
    }

    private void backtrack(StringBuffer s, int i, int dot, List<String> ans) {

        if (i >= s.length() && dot < 0) {
            ans.add(s.substring(0, s.length()-1).toString());
            return;
        }

        if (dot < 0) return;

        for (int j=i+1; j<=Math.min(s.length(), i+3); j++) {
            if (validSlice(s, i, j, dot-1)) {
                s.insert(j, ".");
                backtrack(s, j+1, dot-1, ans);
                s.deleteCharAt(j);
            }
        }

    }

    private boolean validSlice(StringBuffer s, int i, int j, int dot) {
        int len = j-i;

        if (len <=0) return false;
        if (len > 3) return false;
        String sub = s.substring(i, j).toString();

        if (len > 1 && sub.charAt(0) == '0') return false;
        if (Integer.parseInt(sub) > 255) return false;
        return true;
    }

    /**
     * Following is Edctv soln.
     * Unnecessarily complicated.
     */
    private int n;
    private String s;
    private LinkedList<String> segments;
    private ArrayList<String> result;
    private List<String> edctvSol(String str) {
        n = str.length();
        s = str;
        // creating empty lists for storing valid IP addresses,
        // and each segment of IP
        result = new ArrayList < String > ();
        segments = new LinkedList < String > ();
        backtrack(-1, 3);
        return result;
    }

    public boolean valid(String segment) {
        int m = segment.length(); // storing the length of each segment
        if (m > 3) // each segment's length should be less than 3
            return false;
        /*
          Check if the current segment is valid
          for either one of following conditions:
          1. Check if the current segment is less or equal to 255.
          2. Check if the length of the segment is 1. The first character of segment
             can be `0` only if the length of the segment is 1.
        */
        return (segment.charAt(0) != '0') ? (Integer.valueOf(segment) <= 255) : (m == 1);
    }

    // this function will append the current list of segments to the list of results.
    public void updateSegment(int currDot) {
        String segment = s.substring(currDot + 1, n);
        if (valid(segment)) { // if the segment is acceptable
            segments.add(segment); // add it to the list of segments
            // Utility function to concate the entries of the segments list
            // separated by the dot delimeter.
            String ip = String.join(".", segments);
            result.add(ip);
            segments.removeLast(); // remove the top segment
        }
    }

    public void backtrack(int prevDot, int dots) {
        /*
          prevDot : the position of the previously placed dot
          dots : number of dots to place
        */

        // The current dot currDot could be placed in
        // a range from prevDot + 1 to prevDot + 4.
        // The dot couldn't be placed after the last character in the string.
        int maxPos = Math.min(n - 1, prevDot + 4);
        for (int currDot = prevDot + 1; currDot < maxPos; currDot++) {
            String segment = s.substring(prevDot + 1, currDot + 1);
            if (valid(segment)) {
                segments.add(segment); // place dot
                // if all 3 dots are placed add the solution to result
                if (dots - 1 == 0)
                    updateSegment(currDot);
                else
                    // continue to place dots
                    backtrack(currDot, dots - 1);
                segments.removeLast(); // remove the last placed dot
            }
        }
    }
}
