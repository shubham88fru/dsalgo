package lc_cntst;

//@link - https://leetcode.com/problems/maximum-height-of-a-triangle/description/
public class MaximumHeightOfATriangle {
    public int maxHeightOfTriangle(int red, int blue) {
        return Math.max(cal(red, blue), cal(blue, red));

    }

    private int cal(int f, int s) {
        int height = 0;
        boolean first = true;
        int odd = 1;
        int even = 2;
        while (f >= 0 && s >= 0) {
            if (first && f < height+1) break;
            if (!first && s < height+1) break;

            if (first) {
                f -= odd;
                odd += 2;
                first = false;
            } else {
                s -= even;
                even += 2;
                first = true;
            }

            height += 1;
        }

        return height;
    }
}
