package lc_potd;

//@link - https://leetcode.com/problems/count-submatrices-with-all-ones/description/
//@check - https://www.youtube.com/watch?v=dYsJpsgCPzw&ab_channel=codestorywithMIK
public class CountSubmatricesWithAllOnes {
    public int numSubmat(int[][] mat) {
        return mikssol(mat);
    }

    /*
    * Coded by me based on miks explanation
    * but mik's soln was slightly different,
    * which might be slightly efficient.
    * Furthermore, there's an optimal soln
    * using stack, redo if this is a recurring
    * problem for some company.
    * */
    private int mikssol(int[][] mat) {
        int m = mat.length;

        int rects = 0;
        for (int rowStart = 0; rowStart < m; rowStart += 1) {
            for (int rowEnd = rowStart; rowEnd < m; rowEnd += 1) {
                rects += countContinousOnes(rowStart, rowEnd, mat);
            }
        }

        return rects;
    }

    private int countContinousOnes(int rowStart, int rowEnd, int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        int rects = 0;
        int contOne = 0;
        for (int j=0; j<n; j++) {
            int and = 1;
            for (int i=rowStart; i<=rowEnd; i++) {
                and = and & mat[i][j];
                if (and == 0) break;
            }

            if (and == 1) {
                contOne += 1;
                rects += contOne;
            } else contOne = 0;
        }

        return rects;
    }
}
