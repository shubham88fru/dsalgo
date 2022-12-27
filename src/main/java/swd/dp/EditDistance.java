package swd.dp;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/edit-distance/description/
public class EditDistance {
    public int minDistance(String word1, String word2) {
        return minOpsToConvert(word1, word2, 0, word1.length(), 0, word2.length(), new HashMap<String, Integer>());
    }

    private int minOpsToConvert(String word1, String word2, int curr1, int end1, int curr2, int end2, Map<String, Integer> memo) {
        // First string is all proccessed. We'll now need to insert all pending chars in second string.
        if (curr1 >= end1) return end2 - curr2;

        // Second string is all proccessed. We'll now need to delete all extra chars from first string.
        if (curr2 >= end2) return end1 - curr1;

        int insert = 1000001;
        int delete = 1000001;
        int replace = 1000001;
        int noop = 1000001;

        String key = curr1 + "-" + curr2;
        if (memo.containsKey(key)) return memo.get(key);

        if (word1.charAt(curr1) != word2.charAt(curr2)) {

            // Assume that we insert the char at s2 in s1. Once inserted, the curr1 won't change, while curr2 increases.
            insert = 1 + minOpsToConvert(word1, word2, curr1, end1, curr2+1, end2, memo);

            // Assume that we delete the char at s2 from s1. Once deleted, the curr1 increases, while curr2 remains same.
            delete = 1 + minOpsToConvert(word1, word2, curr1+1, end1, curr2, end2, memo);

            // Assume that we replace the char at s2 with s1. Once replaced, the curr1 & curr2 increase.
            replace = 1 + minOpsToConvert(word1, word2, curr1+1, end1, curr2+1, end2, memo);
        } else {
            noop = minOpsToConvert(word1, word2, curr1+1, end1, curr2+1, end2, memo);
        }

        memo.put(key, Math.min(noop, Math.min(insert, Math.min(delete, replace))));
        return memo.get(key);
    }
}
