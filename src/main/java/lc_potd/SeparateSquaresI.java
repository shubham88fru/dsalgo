package lc_potd;

//@link - https://leetcode.com/problems/separate-squares-i/description/
//@check - https://www.youtube.com/watch?v=w0QJQtMxUfk
public class SeparateSquaresI {
    public double separateSquares(int[][] squares) {
        return mikssol(squares);
    }

    /**
         I had a hunch that we'd need to use
         binary search and that at a high level
         how'd we go about solving this problem,
         however there were some details around
         the binary search implementation etc
         specifically due to the precision, which
         I could come up with myself.
     */
    private double mikssol(int[][] squares) {
        int minY = Integer.MAX_VALUE;
        int maxY = 0;
        double totalArea = 0.0;
        int L = 0;

        for (int[] sq: squares) {
            int x = sq[0];
            int y = sq[1];
            int l = sq[2];

            minY = Math.min(minY, y);
            maxY = Math.max(maxY, y+l);
            totalArea += (double)((double)l*(double)l);
        }

        return binarySearch(minY, maxY, totalArea, squares);
    }

    private double binarySearch(int minY, int maxY, double totalArea, int[][] squares) {
        double l = minY;
        double r = maxY;

        while (Math.abs(r-l) > 0.00001) { //this will take care of termination.
            double mid = l + (r-l)/2.0;
            if (eqaulOrLargerBottomArea(squares, totalArea, mid)) {
                r = mid; //not not mid+1 or mid-1; coz +1/-1 may jump the answer.
            } else l = mid;
        }

        return r;
    }

    private boolean eqaulOrLargerBottomArea(int[][] squares, double totalArea, double mid) {
        double bottomArea = 0.0;

        for (int[] sq: squares) {
            int x = sq[0];
            int y = sq[1];
            int l = sq[2];

            if (mid >= (y+l)) {
                bottomArea += ((double)l*(double)l);
            } else if (mid > y) {
                bottomArea += ((double)l * (double)(mid-y));
            }
        }

        return bottomArea >= (totalArea/2.0);
    }
}
