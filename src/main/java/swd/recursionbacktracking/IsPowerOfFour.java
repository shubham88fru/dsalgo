package swd.recursionbacktracking;

//@link - https://leetcode.com/problems/power-of-four/description/
public class IsPowerOfFour {
    public boolean isPowerOfFour(int n) {
        // return iterative(n);
        // return recursive(n);
        return optimal1(n);
        // return wrong(n);
    }

    private boolean iterative(int n) {
        if (n <= 0) return false;

        while (n > 1) {
            if (n%4 != 0) return false;
            n /= 4;
        }

        return n==1;
    }

    private boolean recursive(int n) {
        if (n==1) return true;

        if (n <= 0) return false;
        if (n%4 != 0) return false;

        return recursive(n/4);
    }

    private boolean optimal1(int n) {
        if (n <= 0) return false;

        double div = Math.log10(n)/Math.log10(4);
        return div == (int)div;
    }

    /*
        DOESN'T WORK.
        This trick that I used for power of 3 problem
        doesn't work for this problem.
     */
    private boolean wrong(int n) {
        if (n <= 0) return false;
        /*
            1073741824 -- largest possible 32 bit power of 4
        */
        return 1073741824%n == 0;
    }
}
