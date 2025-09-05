package lc_potd;

//@link - https://leetcode.com/problems/minimum-operations-to-make-the-integer-zero/
//@check - https://www.youtube.com/watch?v=YLKbecs9LIU&ab_channel=codestorywithMIK
public class MinimumOperationsToMakeIntegerZero {
    public int makeTheIntegerZero(int num1, int num2) {
        return pass1(num1, num2);
    }

    /*
        Almost had this, but couldn't solve
        completely on my own because of
        some gaps.

        Idea is to represent lhs as sum
        of powers of 2.

        Took hints from mik.
     */
    private int pass1(long num1, long num2) {

        if (num1 == 0) return 0;

        int k = 1;
        while (true) {
            long lhs = num1-(long)(k*num2); //effective lhs

            if (lhs < 0) return -1;

            int minBits = bits(lhs);

            /**
             This was an important observation.
             A number neads atleast set bits powers
             of two to be represented and at most
             the value of num itself (all as 2^0)

             This is a bit tricky so check mik if needed.
             */
            if (minBits <= k && k <= lhs) return k;

            k += 1;
        }
    }

    private int bits(long num) {
        int c = 0;
        while (num > 0) {
            long res = num & 1l;
            if (res != 0) c += 1;
            num = num >> 1;
        }

        return c;
    }
}

