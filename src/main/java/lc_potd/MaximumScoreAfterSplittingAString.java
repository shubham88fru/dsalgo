package lc_potd;

//@link - https://leetcode.com/problems/maximum-score-after-splitting-a-string/
public class MaximumScoreAfterSplittingAString {
    public int maxScore(String s) {
        int ones = 0;
        for (char ch: s.toCharArray()) {
            if (ch == '1') ones += 1;
        }

        int maxScore = 0;
        int leftScore = 0;
        int rightScore = ones;
        //go till last but one, since each part
        //has to be non empty.
        for (int i=0; i<s.length()-1; i++) {
            char ch = s.charAt(i);
            if (ch == '0') {
                leftScore += 1;
            } else {
                rightScore -= 1;
            }

            maxScore = Math.max(maxScore, leftScore + rightScore);
        }

        return maxScore;
    }
}
