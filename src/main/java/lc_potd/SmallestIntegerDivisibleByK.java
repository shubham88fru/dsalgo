package lc_potd;

import java.util.HashSet;
import java.util.Set;

//@link - https://leetcode.com/problems/smallest-integer-divisible-by-k/description/?
//@check - https://www.youtube.com/watch?v=yqCSa29Ugxc
public class SmallestIntegerDivisibleByK {
    public int smallestRepunitDivByK(int k) {
        // return hintedSol(k);
        return optimal(k);
    }

    private int optimal(int k) {

        int rem = 0;
        for (int len = 1; len <= k; len++) {
            //instead of keeping the num, just keep track of rem, to avoid overflow.
            //This is tricky part.
            rem = (rem*10 + 1)%k;
            if (rem == 0) return len;
        }

        return -1;
    }

    /**
     Set is not needed actually.
     */
    private int hintedSol(int k) {
        Set<Integer> rems = new HashSet<>();

        int len = 0;
        int rem = 0;
        while (true) {

            rem = (rem*10 + 1)%k;
            if (rems.contains(rem)) return -1;
            len += 1;
            if (rem == 0) return len;
            rems.add(rem);
        }

    }
}
