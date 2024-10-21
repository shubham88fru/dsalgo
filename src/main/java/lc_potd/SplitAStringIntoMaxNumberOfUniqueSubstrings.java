package lc_potd;

import java.util.HashSet;
import java.util.Set;

//@link - https://leetcode.com/problems/split-a-string-into-the-max-number-of-unique-substrings/
public class SplitAStringIntoMaxNumberOfUniqueSubstrings {
    public int maxUniqueSplit(String s) {
        return mysol(s, new HashSet<>());
    }

    private int mysol(String s, Set<String> memo) {

        int maxScore = 0;
        for (int i=0; i<s.length(); i++) {
            //duplicate isn't allowed.
            if (memo.contains(s.substring(0, i+1))) continue;

            //since we need to generate max substrings,
            //go character by character, because ideally we would
            //want to split the string as each individual chars.
            memo.add(s.substring(0, i+1));
            int score = 1 + mysol(s.substring(i+1), memo); //check rest.
            memo.remove(s.substring(0, i+1)); //backtrack.
            maxScore = Math.max(score, maxScore);
        }

        return maxScore;
    }
}
