package ptrn.matrices;

import java.util.ArrayList;
import java.util.List;

//@link - https://leetcode.com/problems/spiral-matrix/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6292043317641216
public class SpiralMatrix {

    public List<Integer> spiralOrder(int[][] matrix) {
        return revise(matrix);
    }

    //1) My soln. Edctv soln is diff.
    private List<Integer> revise(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int tb = 0, rb = n-1, bb = m-1, lb = 0; //boundaries - top, right, bottom, and left.
        List<Integer> traversal = new ArrayList<>();

        while (true) {
            for (int j=lb; j<=rb; j++) {
                traversal.add(matrix[tb][j]);
            }
            tb += 1;

            if (tb > bb) break;

            for (int i=tb; i<=bb; i++) {
                traversal.add(matrix[i][rb]);
            }
            rb -= 1;

            if (rb < lb) break;

            for (int j=rb; j>=lb; j--) {
                traversal.add(matrix[bb][j]);
            }
            bb -= 1;

            if (bb < tb) break;

            for (int i=bb; i>=tb; i--) {
                traversal.add(matrix[i][lb]);
            }
            lb += 1;

            if (lb > rb) break;
        }

        return traversal;
    }

    //2) Edctv soln.
    public static List<Integer> spiralOrderEdctv(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int row = 0;
        int col = -1;
        int direction = 1;
        List<Integer> result = new ArrayList<>();

        while (rows > 0 && cols > 0) {
            for (int i = 0; i < cols; i++) {
                col += direction;
                result.add(matrix[row][col]);
            }
            rows--;

            for (int i = 0; i < rows; i++) {
                row += direction;
                result.add(matrix[row][col]);
            }
            cols--;

            direction *= -1;
        }

        return result;
    }

}
