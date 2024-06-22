package lc_potd;

import java.util.HashSet;
import java.util.Set;

//@link - https://leetcode.com/problems/grumpy-bookstore-owner/
public class GrumpyBookstoreOwner {
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int fixedScore = 0;
        for (int i=0; i<grumpy.length; i++) {
            if (grumpy[i] == 0) fixedScore += customers[i];
        }

        Set<Integer> grumpyIndexes = new HashSet<>();
        int l = 0;
        int r = 0;
        int score = 0;
        while (r < customers.length) {
            if (r-l+1 <= minutes) {
                if (grumpy[r] == 1) grumpyIndexes.add(r);

                if (r-l+1 == minutes) {
                    int sc = 0;
                    for (int i: grumpyIndexes) {
                        sc += customers[i];
                    }
                    score = Math.max(fixedScore + sc, score);
                }
                r += 1;

            } else {
                if (grumpyIndexes.contains(l)) grumpyIndexes.remove(l);
                l += 1;
            }
        }

        return score;
    }
}
