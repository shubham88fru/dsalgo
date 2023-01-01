package swd.hashmap;

//@link - https://leetcode.com/problems/matrix-block-sum/description/
public class MatrixBlockSum {
    public int[][] matrixBlockSum(int[][] mat, int k) {
        int rowlen = mat.length;
        int collen = mat[0].length;

        int[][] res = new int[rowlen][collen];
        for (int i=0; i<rowlen; i++) {
            for (int j=0; j<collen; j++) {
                int val = 0;

                //limits for r
                int rlow = i-k;
                int rhigh = i+k;

                //limits for c
                int clow = j-k;
                int chigh = j+k;

                //evaluate res[c][r]
                for (int r = rlow; r<=rhigh; r++) {
                    //ignore -ve values in range and ensure `r` doesn't bypass original matrix's rowlen
                    if (r>=0 && r<rowlen) {
                        for (int c = clow; c<=chigh; c++) {
                            //ignore -ve values in range and ensure `c` doesn't bypass original matrix's collen.
                            if (c>=0 && c<collen) {
                                val += mat[r][c];
                            }
                        }
                    }
                }
                res[i][j] = val;
            }
        }
        return res;
    }
}
