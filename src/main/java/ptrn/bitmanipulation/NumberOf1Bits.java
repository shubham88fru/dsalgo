package ptrn.bitmanipulation;

//@link - https://leetcode.com/problems/number-of-1-bits/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5550655923290112
public class NumberOf1Bits {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        // return getSetBits(n);
        return revise(n);
    }

    private int revise(int n) {
        int count = 0;
        while (n > 0) {
            count += n & 1;
            n = n >> 1;
        }

        return count;
    }

    private int getSetBits(int n) {
        if (n == 0) return 0;

        int ans = 0;
        if ((n & 1) != 0) ans = 1 + getSetBits(n >>> 1);
        else ans = getSetBits(n >>> 1);

        return ans;
    }
}
