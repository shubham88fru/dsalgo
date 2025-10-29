package lc_potd;

//@link - https://leetcode.com/problems/smallest-number-with-all-set-bits/description/
public class SmallestNumberWithAllSetBits {
    public int smallestNumber(int n) {
        // return brute(n);
        return optimal(n);
    }


    /**
     Imp observation that
     for a num that is power of 2, the
     number just before it has all 1s.
     (except 2 power 0, ofc)
     */
    private int optimal(int n) {

        int ans = 1; // first all ones num
        while (ans < n) {
            ans = 2*ans + 1; //subsequent all ones num
        }
        return ans;
    }

    private int brute(int n) {

        while (true) {
            if (n%2 != 0) {
                if (all1s(n)) return n;
            }
            n += 1;
        }

    }

    /**
     Smart way to check if
     in a number are set.

     It makes sense if you think
     about it with and example.
     say n=7.
     */
    private boolean all1s(int n) {
        return ((n & (n+1)) == 0);
    }

    /**
     Naive way to check if all bits
     are set in a given number.
     */
    private boolean allOneBits(int n) {
        while (n > 0) {
            int bit = n & 1;
            if (bit == 0) return false;
            n = n >> 1;
        }

        return true;
    }
}
