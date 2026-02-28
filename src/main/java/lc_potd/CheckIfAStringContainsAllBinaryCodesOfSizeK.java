package lc_potd;

import java.util.HashSet;
import java.util.Set;

//@link - https://leetcode.com/problems/check-if-a-string-contains-all-binary-codes-of-size-k/?
public class CheckIfAStringContainsAllBinaryCodesOfSizeK {
    public boolean hasAllCodes(String s, int k) {
        return pass1(s, k);
    }

    /*
        My sol.
     */
    private boolean pass1(String s, int k) {
        int n = s.length();
        Set<String> st = new HashSet<>();

        int l=0, r=0;
        while (r<n) {
            while (r<n && r-l<k) {
                r += 1;
            }
            st.add(s.substring(l, r));
            l += 1;
        }


        return st.size() >= Math.pow(2, k);
    }
}
