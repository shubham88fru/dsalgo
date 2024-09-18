package ptrn.bitmanipulation;

//@link - https://leetcode.com/problems/reverse-bits/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6465981083025408
public class ReverseBits {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        long ans = 0;
        for (int i=31; i >= 0; i--) {
            long num = (n&(1<<(31-i)));
            if (num != 0) {
                ans += Math.pow(2, i);
            }
        }
        return (int)ans;
    }
}
