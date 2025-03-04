package lc_potd;

//@link - https://leetcode.com/problems/check-if-number-is-a-sum-of-powers-of-three/description/
//@check - https://www.youtube.com/watch?v=BpotFrERzP4&t=2058s&ab_channel=codestorywithMIK
public class CheckIfANumberIsASumOfPowersOfThree {
    public boolean checkPowersOfThree(int n) {
        // return pass1(n);
        return pass2(n);
    }

    //1) Math approach - based on mik's explanation.
    private boolean pass2(int n) {
        int p = (int) Math.log(n)/(int)Math.log(3);
        while (n > 0) {
            if (n >= (int) Math.pow(3, p)) {
                n -= (int) Math.pow(3, p);
            }

            if (n >= (int) Math.pow(3, p)) {
                return false;
            }

            p -= 1;
        }

        return true;
    }

    //2) My DP soln.
    private boolean pass1(int n) {
        return dp(n, 0, 15); //max power of 3 is 15 because of given constraints.
    }

    private boolean dp(int n, int pow, int maxPow) {
        if (n == 0) return true;
        if (n < 0) return false;
        if (pow > maxPow) return false;

        boolean select = dp(n-(int)Math.pow(3, pow), pow + 1, maxPow);
        boolean notSelect = dp(n, pow+1, maxPow);

        return select || notSelect;
    }
}
