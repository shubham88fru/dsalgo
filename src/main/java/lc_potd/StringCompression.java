package lc_potd;

//@link - https://leetcode.com/problems/string-compression/
public class StringCompression {
    public int compress(char[] chars) {
        return optimal(chars);
    }

    /**
     * My soln.
     * This code is completely based on my
     * soln for  {@link lc_potd.RunLengthEncoding}
     * Revise it first, before solving this,
     * This problem is very easy once that is
     * revised.
     */
    private int optimal(char[] s) {
        int n = s.length;
        int l=0, r=0, i=0, count=1;

        while (l < n) {
            while (r < n-1 && s[r] == s[r+1]) {
                r += 1;
                count += 1;
            }

            s[i] = s[l];
            i += 1;
            if (count > 1) {
                String cnt = String.valueOf(count);
                for (int j=0; j<cnt.length(); j++) {
                    s[i++] = cnt.charAt(j);
                }
            }

            r += 1;
            l = r;
            count = 1;
        }

        return i;
    }
}
