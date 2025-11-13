package lc_potd;

//@link - https://leetcode.com/problems/maximum-number-of-operations-to-move-ones-to-the-end/?
public class MaximumNumberOfOperationsToMoveOnesToTheEnd {
    public int maxOperations(String s) {
        return pass1(s);
    }

    private int pass1(String s) {
        int n = s.length();

        int i=0;
        int ones = 0;
        int ops = 0;
        while (i < n) {
            char ch = s.charAt(i);
            if (ch == '1') {
                ones += 1;
                i += 1;
            } else {
                while (i < n && s.charAt(i) == '0') {
                    i += 1;
                }
                ops += ones;
            }
        }

        return ops;
    }
}

