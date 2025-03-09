package ptrn.bitmanipulation;

//@link - https://leetcode.com/problems/reverse-integer/
//@check - https://www.youtube.com/watch?v=HAgLH58IgJQ&t=610s&ab_channel=NeetCode
public class ReverseInteger {
    public int reverse(int x) {
        return ncsol(x);
    }

    /*
        Based on NC's explanation.
    */
    private int ncsol(int x) {
        final int MAX = Integer.MAX_VALUE;
        final int MIN = Integer.MIN_VALUE;

        int res = 0;
        while (x != 0) {
            /*
                Not sure why we don't have to do the
                mod trick that I usually do when moding -ves
                but this works like magic.
            */
            int lastDig = x%10;
            x /= 10;
            if (res > MAX/10 || (res == MAX/10 && lastDig >= MAX%10)) return 0;
            if (res < MIN/10 || (res == MIN/10 && lastDig <= MIN%10)) return 0;

            res = (res*10) + lastDig;
        }

        return res;
    }
}
