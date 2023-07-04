package interview;

//@link - https://leetcode.com/problems/number-of-1-bits/
//@company - JSW one
public class NumOfSetsBits {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        return getSetBits(n);
    }

    private int getSetBits(int n) {
        if (n == 0) return 0;

        int ans = 0;
        if ((n & 1) != 0) ans = 1 + getSetBits(n >>> 1);
        else ans = getSetBits(n >>> 1);

        return ans;
    }
}
