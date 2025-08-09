package swd.recursionbacktracking;

//@link - https://leetcode.com/problems/power-of-two/description/
public class IsPowerOfTwo {
    /*** My Soln ***/
    public boolean isPowerOfTwo(int n) {
        if (n == 1) return true;
        else if (n < 1) return false;

        return (n%2 == 0) && isPowerOfTwo(n/2);
    }

    /**
     * Most optimal using bitmagic
     */
    private boolean mostOptimal(int n) {
        if (n <= 0) return false;
        return ((n&(n-1)) == 0);
    }

    /*
        my constant time soln.
     */
    private boolean revise(int n) {
        if (n < 0) return false;

        int oc = 0;
        for (int i=0; i<32; i++) {
            int and = n&(1<<i);
            if (and != 0) oc += 1;
        }
        return oc == 1;
    }

    private boolean revise2(int n) {
        if (n <= 0) return false;
        return recursion(n);
    }

    private boolean recursion(int n) {
        if (n==1) return true;
        if (n%2 != 0) return false;
        return recursion(n/2);
    }
}
