package lc_potd;

//@link - https://leetcode.com/problems/minimum-suffix-flips/?
public class MinimumSuffixFlips {
    public int minFlips(String target) {
        return pass1(target);
    }

    /*
        My soln.
     */
    private int pass1(String target) {
        int n = target.length();
        int flips = 0;
        for (int i=0; i<n; i++) {
            char ch = target.charAt(i);
            if (flips%2 == 0) {
                if (ch != '0') flips += 1;
            } else {
                if (ch != '1') flips += 1;
            }
        }

        return flips;
    }
}
