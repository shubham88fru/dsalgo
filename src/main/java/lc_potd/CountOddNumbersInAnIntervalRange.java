package lc_potd;

//@link - https://leetcode.com/problems/count-odd-numbers-in-an-interval-range/?
public class CountOddNumbersInAnIntervalRange {
    public int countOdds(int low, int high) {
        return revise(low, high);
    }

    /**
     O(1) soln is completely
     observation based.
     */
    private int revise(int l, int h) {
        if (l%2 == 0) return (h-l+1)/2;

        return (int)Math.ceil((h-l+1)/2.0);
    }
}
