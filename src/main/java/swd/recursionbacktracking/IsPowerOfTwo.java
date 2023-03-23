package swd.recursionbacktracking;

//@link - https://leetcode.com/problems/power-of-two/description/
public class IsPowerOfTwo {
    /*** My Soln ***/
    public boolean isPowerOfTwo(int n) {
        if (n == 1) return true;
        else if (n < 1) return false;

        return (n%2 == 0) && isPowerOfTwo(n/2);
    }
}
