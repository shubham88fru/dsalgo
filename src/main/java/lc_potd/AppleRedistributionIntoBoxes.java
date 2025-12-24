package lc_potd;

import java.util.Arrays;

//@link - https://leetcode.com/problems/apple-redistribution-into-boxes/description/
public class AppleRedistributionIntoBoxes {
    public int minimumBoxes(int[] apple, int[] capacity) {
        return revise(apple, capacity);
    }

    /**
     * My sorting soln.
     * Can be solved in linear time
     * using counting sort approach as well.
    */
    private int revise(int[] apple, int[] capacity) {
        int m = capacity.length;

        int sum = 0;
        for (int a: apple) sum += a;


        Arrays.sort(capacity);
        int i=m-1;
        for (; i>=0; i--) {
            sum -= capacity[i];
            if (sum <= 0) break;
        }

        return m-i;
    }
}
