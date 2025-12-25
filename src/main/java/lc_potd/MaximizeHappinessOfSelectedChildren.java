package lc_potd;

import java.util.Arrays;

//@link - https://leetcode.com/problems/maximize-happiness-of-selected-children/?
public class MaximizeHappinessOfSelectedChildren {
    public long maximumHappinessSum(int[] happiness, int k) {
        Arrays.sort(happiness);
        int n = happiness.length;
        long sum = 0;
        long minus = 0;
        for (int i=n-1; i>=(n-1-k+1); i--) {
            sum += (happiness[i] - minus) > 0 ? (happiness[i] - minus): 0;
            minus += 1;
        }

        return sum;
    }
}
