package lc_potd;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//@link - https://leetcode.com/problems/find-the-longest-substring-containing-vowels-in-even-counts/description/
public class FindTheLongestSubstringContainingVowelsInEvenCounts {
    //my soln
    public int findTheLongestSubstring(String s) {
        /**
         At first, the immediate intuition was to
         solve this question using sliding window.
         But I don't think that is easily doable, because we
         have keep track of frequency of vowels in each window
         and more than anything, after reading the question and
         trying the examples, I don't think that sliding
         window even applies to this question.

         So did this using prefix xor. Hint was that, many longest
         substring or sequence type of question can be solved using
         prefix sum pattern, moreover, since the question mentioned
         that it needs even frequency of each vowel, that was another
         hint to use xor, coz xor cancels out in case of even.
         */

        Set<Character> vowels = new HashSet<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');

        Map<Integer, Integer> pxor = new HashMap<>();
        int px = 0;
        pxor.put(px, -1);

        int maxLen = 0;
        for (int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);
            if (vowels.contains(ch)) {
                /**
                 If current char is not a vowel,
                 just ignore updating the pxor
                 and go forward checking the
                 maxLen or updating the map.

                 We need to ensure that the vowels are
                 all even count, and so we'll only xor
                 the vowels.
                 */
                px ^= ch;
            }
            if (pxor.containsKey(px)) {
                /**
                 If the xor has already been seen before,
                 don't update the map with new index, because
                 we wan't to find the largest substring, for
                 which we need the earliest occurrence of a prefix xor.

                 If a pxor has already been seen before means the effective
                 xor in the range is zero, which means,
                 the vowels in the range of last seen index and curr
                 are all even, otherwise, the effective xor
                 in the range wouldn't have been zero and the pxor wouldn't
                 have appeared again.
                 */
                maxLen = Math.max(i-pxor.get(px), maxLen);
            } else {
                pxor.put(px, i);
            }
        }

        return maxLen;

    }
}
