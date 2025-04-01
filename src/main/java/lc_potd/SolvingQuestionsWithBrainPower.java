package lc_potd;

//@link - https://leetcode.com/problems/solving-questions-with-brainpower/description/
//@check - https://www.youtube.com/watch?v=NcC8lo7nLCE&ab_channel=codestorywithMIK
public class SolvingQuestionsWithBrainPower {
    public long mostPoints(int[][] questions) {
        // return pass1(questions);
        return bottomup(questions);
    }

    /*
        1) My soln with topdown approach.
    */
    private long pass1(int[][] questions) {

        return topdown(questions, 0, new Long[questions.length]);
    }

    private long topdown(int[][] questions, int i,  Long[] cache) {
        if (i >= questions.length) return 0;

        if (cache[i] != null) return cache[i];

        long solve = (long)questions[i][0] + topdown(questions, i+questions[i][1]+1, cache);
        long skip = topdown(questions, i+1, cache);

        cache[i] =  Math.max(solve, skip);
        return Math.max(solve, skip);
    }


    /*
        2) Based on Mik's soln for bottom up.
    */
    private long bottomup(int[][] questions) {
        int n = questions.length;

        //max points gained by questions from i to n-1.
        long[] dp = new long[n+1];

        for (int i = n-1; i >= 0; i--) {
            int next = i + questions[i][1] + 1;
            dp[i] = Math.max(
                    questions[i][0] + (next >= n ? 0: dp[next]), //solve
                    dp[i+1] //skip
            );
        }

        return dp[0];

    }
}
