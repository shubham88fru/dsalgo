package lc_potd;

import java.util.Arrays;

//@link - https://leetcode.com/problems/successful-pairs-of-spells-and-potions/description/
public class SuccessfulPairsOfSpellsAndPotions {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        return pass1(spells, potions, success);
    }

    private int[] pass1(int[] spells, int[] potions, long success) {
        int n = spells.length;

        Arrays.sort(potions);

        int[] ans = new int[n];
        for (int i=0; i<n; i++) {
            ans[i] = binarySearch(spells[i], potions, success);
        }

        return ans;
    }

    private int binarySearch(int spell, int[] potions, long success) {
        int m = potions.length;
        int l = 0;
        int r = m-1;

        int idx = -1;
        while (l <= r) {
            int mid = l + (r-l)/2;
            if ((long)((long)potions[mid]*(long)spell) >= success) {
                idx = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        if (idx == -1) return 0;

        return m-idx;
    }
}
