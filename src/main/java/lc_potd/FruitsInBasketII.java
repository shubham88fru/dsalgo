package lc_potd;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/fruits-into-baskets-ii/
public class FruitsInBasketII {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        return brute(fruits, baskets);
    }

    /*
    * This is my brute force.
    * Optimal way is to use segment trees for this and
    * part III for this problem. Mik showed the segment
    * tree approach. check if this is a hfq for some company.
    * */
    private int brute(int[] fruits, int[] baskets) {
        // int m = fruits.length;
        int n = baskets.length;

        Map<Integer, Integer> placement = new HashMap<>();
        int cnt = 0;
        for (int fruit: fruits) {
            boolean placed = false;
            for (int j=0; j<n; j++) {
                if (placement.containsKey(j)) continue;
                if (fruit <= baskets[j]) {
                    placement.put(j, fruit);
                    placed = true;
                    break;
                }
            }
            if (!placed) cnt += 1;
        }

        return cnt;
    }
}
