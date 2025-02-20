package lc_potd;

import java.util.HashSet;
import java.util.Set;

//@link - https://leetcode.com/problems/find-unique-binary-string/description/
//@check - https://www.youtube.com/watch?v=8Cay8q3BOx4&ab_channel=codestorywithMIK
public class FindUniqueBinaryString {
    public String findDifferentBinaryString(String[] nums) {
        // return sol2(nums);
        return sol1(nums);
    }

    /* O(n) soln. Mik's approach. Optimal. */
    private String sol1(String[] nums) {
        StringBuffer sb = new StringBuffer();

        int pos = 0;
        for (String num: nums) {
            char ch = num.charAt(pos);
            if (ch == '0') sb.append('1');
            else if (ch == '1') sb.append('0');

            pos += 1;
        }

        return sb.toString();
    }

    /*
        My backtracking soln. Works because the
        constraints are small.
    */
    private String sol2(String[] nums) {
        StringBuffer sb = new StringBuffer();
        boolean[] found = {false};

        Set<String> st = new HashSet<>();
        for (int i=0; i<nums.length; i++) {
            st.add(nums[i]);
        }

        pass1(nums.length, sb, st, found);

        return sb.toString();
    }

    private void pass1(int n, StringBuffer sb, Set<String> st, boolean[] found) {

        if (sb.length() == n) {
            if (!st.contains(sb.toString())) {
                found[0] = true;
            }
            return;
        }

        for (int i=0; i<2; i++) {
            sb.append(i);
            pass1(n, sb, st, found);
            if (found[0]) return;
            sb.deleteCharAt(sb.length()-1);
        }
    }
}
