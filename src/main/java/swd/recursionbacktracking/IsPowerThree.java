package swd.recursionbacktracking;

//@link - https://leetcode.com/problems/power-of-three/description/
public class IsPowerThree {
    /*** My Soln ***/
    public boolean isPowerOfThree(int n) {
        if (n == 1) return true;
        else if (n < 1) return false;

        return (n%3 == 0) && isPowerOfThree(n/3);
    }
}
