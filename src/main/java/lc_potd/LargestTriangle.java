package lc_potd;

//@link - https://leetcode.com/problems/largest-triangle-area/description/?
public class LargestTriangle {
    public double largestTriangleArea(int[][] points) {
        return brute(points);
    }

    private double brute(int[][] points) {
        double maxArea = 0.0;

        for (int i=0; i<points.length; i++) {
            for (int j=i+1; j<points.length; j++) {
                for (int k=j+1; k<points.length; k++) {
                    int x1 = points[i][0];
                    int y1 = points[i][1];

                    int x2 = points[j][0];
                    int y2 = points[j][1];

                    int x3 = points[k][0];
                    int y3 = points[k][1];

                    /**
                     People are boasting in the comments
                     that the following formula was taught
                     in school. Not sure if I'm old enough
                     to remember, but can't recollect seeing
                     or applying this formula ever in my life.

                     Shoelace's formula is used to find the are of
                     triangle formed by any 3 given points -
                     Area = (1/2) * |x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)| 🤯
                     */
                    double area = 0.5*(Math.abs(x1*(y2-y3) + x2*(y3-y1) + x3*(y1-y2)));
                    maxArea = Math.max(maxArea, area);
                }
            }
        }

        return maxArea;
    }
}
