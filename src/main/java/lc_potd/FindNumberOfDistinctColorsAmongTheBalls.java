package lc_potd;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/find-the-number-of-distinct-colors-among-the-balls/description/
public class FindNumberOfDistinctColorsAmongTheBalls {
    public int[] queryResults(int limit, int[][] queries) {
        return pass1(limit, queries);
    }

    /*
        Following is my soln. Not sure why they gave this limit param.
        Optimal approach doesn't need limit. Even mik had similar approach.
     */
    private int[] pass1(int limit, int[][] queries) {
        int n = queries.length;

        Map<Integer, Integer> B_C = new HashMap<>(); //ball v/s its color.
        Map<Integer, Integer> C_F = new HashMap<>(); //count of each color so far.

        int[] ans = new int[n];
        for (int i=0; i<n; i++) {
            int ball = queries[i][0];
            int color = queries[i][1];

            if (B_C.containsKey(ball)) {
                int currColor = B_C.get(ball);
                C_F.put(currColor, C_F.get(currColor)-1);
                if (C_F.get(currColor) == 0) C_F.remove(currColor);
            }

            B_C.put(ball, color);
            C_F.put(color, C_F.getOrDefault(color, 0)+1);

            ans[i] = C_F.size();
        }

        return ans;
    }
}
