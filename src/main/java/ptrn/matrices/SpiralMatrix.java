package ptrn.matrices;

import java.util.ArrayList;
import java.util.List;

//@link - https://leetcode.com/problems/spiral-matrix/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6292043317641216
public class SpiralMatrix {
    //1) My soln. Edctv soln is diff.
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        //start from top left
        int i = 0;
        int j = 0;
        List<Integer> ans = new ArrayList<>();
        int[][] visited = new int[m][n];

        //visit the first cell.
        ans.add(matrix[i][j]);
        visited[i][j] = -1;

        //If can move right, down, left or up..
        while (canMove(i, j+1, m, n, visited)
                || canMove(i+1, j, m, n, visited)
                || canMove(i, j-1, m, n, visited)
                || canMove(i-1, j, m, n, visited)
        ) {
            //Note: This order matters. To move in a spiral
            //fashion - we first move right, then down, then left and then up.
            //After all this we repeat.

            //As long as can move right, keep moving right.
            while (canMove(i, j+1, m, n, visited)) {
                //visit.
                ans.add(matrix[i][j+1]);
                visited[i][j+1] = -1;
                j += 1;
            }

            //Then, as long as can move down, move down.
            while (canMove(i+1, j, m, n, visited)) {
                //visit.
                ans.add(matrix[i+1][j]);
                visited[i+1][j] = -1;
                i += 1;
            }

            //Then, as long as can move left, move left.
            while (canMove(i, j-1, m, n, visited) ) {
                //visit.
                ans.add(matrix[i][j-1]);
                visited[i][j-1] = -1;
                j -= 1;
            }

            //Then, as long as can move up, move up.
            while (canMove(i-1, j, m, n, visited)) {
                //visit.
                ans.add(matrix[i-1][j]);
                visited[i-1][j] = -1;
                i -= 1;
            }
        }

        return ans;
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

    //checks if can move to suplied position.
    //The position should be in bouds and not have been visited already.
    private boolean canMove(int i, int j, int m, int n, int[][] visited) {
        return (i >=0 && i < m) && (j >=0 && j < n) && (visited[i][j] != -1);
    }
}
