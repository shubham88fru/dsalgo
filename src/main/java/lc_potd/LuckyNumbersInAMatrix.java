package lc_potd;

import java.util.ArrayList;
import java.util.List;

//@link - https://leetcode.com/problems/lucky-numbers-in-a-matrix/description/
public class LuckyNumbersInAMatrix {
    public List<Integer> luckyNumbers (int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        int[] row_mins = new int[matrix.length];

        for (int i=0; i<matrix.length; i++) {
            int min = Integer.MAX_VALUE;
            for (int j=0; j<matrix[0].length; j++) {
                if (matrix[i][j] < min) min = matrix[i][j];
            }
            row_mins[i] = min;
        }

        for (int j=0; j<matrix[0].length; j++) {
            int max = Integer.MIN_VALUE;
            int row = 0;
            for (int i=0; i<matrix.length; i++) {
                if (matrix[i][j] > max) {
                    row = i;
                    max = matrix[i][j];
                }
            }

            //coz its given that matrix contains
            //only distinct elements. So, if a num
            //of the column and row_mins array are equal,
            //mean they are the same element.
            if (max == row_mins[row]) ans.add(max);
        }

        return ans;
    }
}
