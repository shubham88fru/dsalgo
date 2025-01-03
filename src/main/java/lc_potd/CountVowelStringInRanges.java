package lc_potd;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//@link - https://leetcode.com/problems/count-vowel-strings-in-ranges/
public class CountVowelStringInRanges {
    public int[] vowelStrings(String[] words, int[][] queries) {
        int n = words.length;
        int[] pc = new int[n];

        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        if (vowels.contains(words[0].charAt(0)) && vowels.contains(words[0].charAt(words[0].length()-1))) {
            pc[0] = 1;
        }

        for (int i = 1; i<n; i++) {
            char f = words[i].charAt(0);
            char l = words[i].charAt(words[i].length()-1);

            if (vowels.contains(f) && vowels.contains(l)) {
                pc[i] = pc[i-1] + 1;
            } else pc[i] = pc[i-1];
        }

        int[] ans = new int[queries.length];
        int idx = 0;
        for (int[] query: queries) {
            int l = query[0]-1;
            int r = query[1];

            if (l < 0) ans[idx++] = pc[r];
            else {
                ans[idx++] = pc[r] - pc[l];
            }
        }

        return ans;
    }
}
