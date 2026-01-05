package lc_potd;

//@link - https://leetcode.com/problems/maximum-matrix-sum/
public class MaximumMatrixSum {

    //my sol.
    /**
     * The most important point of the problem is
     * that no matter what, since we can apply the
     * ops any number of times, we can reduce the
     * negative count to 0 or 1.
     * */
    public long maxMatrixSum(int[][] matrix) {
        int negCnt = 0;
        int minMag = Integer.MAX_VALUE;
        long sum = 0;

        int m = matrix.length;
        int n = matrix[0].length;
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                //keep counting the num of -ves.
                if (matrix[i][j] < 0) negCnt += 1;

                //keep track of the num with the least absolute value.
                minMag = Math.min(minMag, Math.abs(matrix[i][j]));

                //sum all assuming each of them are +ve.
                sum += Math.abs(matrix[i][j]);
            }
        }

        /*
         If num of -ve nums in the matrix are even,
         then no matter where each num is, we can make
         all of them positive.
         */
        if(negCnt%2 == 0) return sum;

        /*
            Otherwise, one num will end being -ve.
            So, we'll make the smallest num negative so
            the sum is max.
         */
        return (sum - minMag - minMag);

    }
}
