package lc_potd;

//@link - https://leetcode.com/problems/count-commas-in-range/?
public class CountCommasInRange {
    public int countCommas(int n) {
        return pass1(n);
    }

    private int pass1(int n) {
        if (n < 1000) return 0;
        return n-999;
    }
}
