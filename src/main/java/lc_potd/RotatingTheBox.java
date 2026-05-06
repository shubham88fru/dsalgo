package lc_potd;
import java.util.Arrays;

//@link - https://leetcode.com/problems/rotating-the-box/
public class RotatingTheBox {
    /*
        Mik showed an optimal approach as well,
        check if this is a recurring problem for some company.
     */
    public char[][] rotateTheBox(char[][] box) {
        int m = box.length;
        int n = box[0].length;

        for (int j=n-1; j>=0; j--) {
            for (int i=0; i<m; i++) {
                if (box[i][j] == '#') {
                    int jj = j;
                    while (jj+1< n && box[i][jj+1] != '*' && box[i][jj+1] != '#') {
                        jj += 1;
                    }
                    box[i][j] = '.';
                    box[i][jj] = '#';
                }
            }
        }
        return transfrom(box);
    }

    private char[][] transfrom(char[][] box) {
        int m = box.length;
        int n = box[0].length;

        char[][] tr = new char[n][m];
        int col = m-1;
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                tr[j][col] = box[i][j];
            }
            col -= 1;
        }
        // printMatrix(tr);
        return tr;
    }

    /// /////////////////////////////////////////////
    private char[][] suboptimal(char[][] boxGrid) {
        int m = boxGrid.length;
        int n = boxGrid[0].length;

        for (int i=0; i<m; i++) {
            for (int j=n-1; j>=0; j--) {
                if (boxGrid[i][j] == '#') {
                    int jj = j+1;
                    int pos = -1;
                    while (jj < n && boxGrid[i][jj] != '*' && boxGrid[i][jj] == '.') {
                        pos = jj;
                        jj += 1;
                    }

                    if (pos != -1) {
                        boxGrid[i][pos] = '#';
                        boxGrid[i][j] = '.';
                    }
                }
            }
        }

        char[][] mat = rotate(boxGrid);
        return mat;
    }

    private char[][] rotate(char[][] boxGrid) {
        int m = boxGrid.length;
        int n = boxGrid[0].length;

        char[][] rotated = new char[n][m];
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                rotated[j][i] = boxGrid[i][j];
            }
        }

        for (int i=0; i<n; i++) {
            int l=0, r=m-1;
            while (l <= r) {
                char temp = rotated[i][l];
                rotated[i][l] = rotated[i][r];
                rotated[i][r] = temp;
                l += 1;
                r -= 1;
            }
        }

        return rotated;
    }
    /// /////////////////////////////////////////////

    private void printMatrix(char[][] box) {
        int m = box.length;
        int n = box[0].length;
        for (int i=0; i<m; i++) {
            System.out.println(Arrays.toString(box[i]));
        }
    }
}
