package lc_potd;

//@link - https://leetcode.com/problems/maximum-area-of-longest-diagonal-rectangle/
public class MaximumAreaOfLongestDiagonalRectangle {
    public int areaOfMaxDiagonal(int[][] dimensions) {
        return pass1(dimensions);
    }

    private int pass1(int[][] dimensions) {
        int m = dimensions.length;
        int n = dimensions[0].length;

        double maxD = -1.0;
        int maxA = 0;
        for (int i=0; i<m; i++) {
            int l = dimensions[i][0];
            int w = dimensions[i][1];

            double d = l*l + w*w; //don't really need to take sqrt
            if (d > maxD) {
                maxD = d;
                maxA = l*w;
            } else if (d == maxD) {
                maxA = Math.max(maxA, l*w);
            }
        }

        return maxA;
    }
}
