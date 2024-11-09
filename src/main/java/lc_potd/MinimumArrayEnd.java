package lc_potd;

//@link - https://leetcode.com/problems/minimum-array-end/
//@check - https://www.youtube.com/watch?v=rChLZzzggjo&t=22s&ab_channel=codestorywithMIK
public class MinimumArrayEnd {
    public long minEnd(int n, int x) {
        int i = 0;
        long num = x;

        while (i < n) {
            if ((num & x) != x)  {
                num = x | num;
            }

            num += 1;
            i += 1;
        }

        return num-1;
    }
}
