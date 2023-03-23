package swd.recursionbacktracking;

//@link - https://leetcode.com/problems/power-of-four/description/
public class IsPowerOfFour {
    /*** My Soln ***/
    public boolean isPowerOfFour(int n) {
        if (n == 1) return true;
        else if (n < 1) return false;

        return (n%4 == 0) && isPowerOfFour(n/4);
    }
}
