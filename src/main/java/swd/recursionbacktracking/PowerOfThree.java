package swd.recursionbacktracking;

//@link - https://leetcode.com/problems/power-of-three/description/
public class PowerOfThree {
    public boolean isPowerOfThree(int n) {
        // return revise1(n);
        // return revise2(n);
        // return approach3(n);
        return optimal(n);
    }

    private boolean optimal(int n) {
        if (n <= 0) return false;

        // int largestMultipleOf3 = (int)Math.pow(3, 19); //largest multiple of 3 that can fit in 32 bits.
        int largestMultipleOf3 = 1162261467; //hardcoded, largest possible 32 bit multiple of 3.
        return largestMultipleOf3%n == 0;
    }

    /**
     Constant time, w/o loop/recursion.
     */
    private boolean approach3(int n) {
        if (n <= 0) return false;

        /*
            Not sure why simply doing this doesn't work.
            return Math.log10(n)%Math.log10(3) == 0;
         */
        double x = Math.log10(n)/Math.log10(3);
        return x == (int)x; //check if x is an integer.
    }

    private boolean revise1(int n) {
        if (n <= 0) return false;

        if (n==1) return true;
        while (n > 1) {
            if (n%3 != 0) return false;
            n /= 3;
        }

        return n==1;
    }

    private boolean revise2(int n) {
        if (n <= 0) return false;
        if (n==1) return true;

        if (n%3 != 0) return false;
        return revise2(n/3);
    }
}
