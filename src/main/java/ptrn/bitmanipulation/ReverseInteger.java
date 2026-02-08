package ptrn.bitmanipulation;

//@link - https://leetcode.com/problems/reverse-integer/
//@check - https://www.youtube.com/watch?v=HAgLH58IgJQ&t=610s&ab_channel=NeetCode
public class ReverseInteger {
    public int reverse(int x) {
        // return approach1(x);
        // return approach2(x);
        return optimal(x);
    }

    /**
     Without using long.
     */
    private int optimal(int x) {
        final int MAX = Integer.MAX_VALUE;
        final int MIN = Integer.MIN_VALUE;

        int rev = 0;
        while (x != 0) {
            /**
             What can cause overflow?
             `rev*10` or `rev*10+x%10`
             Therefore, we proactively check
             if doing any of this will cause
             overflow.
             */
            int dig = x%10;

            if (rev > MAX/10 || (rev == MAX/10 && dig >= MAX%10)) return 0;
            if (rev < MIN/10 || (rev == MIN/10 && dig <= MIN%10)) return 0;

            rev = (rev * 10 + x%10);
            x /= 10;
        }

        return rev;
    }

    /**
     We don't really need to take the
     abs. Reversing works for negative
     just as it does for +ve. I know that
     sounds a bit crazy but it just works.
     */
    private int approach2(int x) {
        long rev = 0;
        while (x !=0) {
            rev = (rev * 10 + x%10);
            if (rev > Integer.MAX_VALUE) return 0; //+ve overflow.
            if (rev < Integer.MIN_VALUE) return 0; //-ve overflow.
            x /= 10;
        }

        return (int)rev;
    }

    /**
     Using long, to avoid int overflow
     absurdity. However, the question
     asks to not use long.
     */
    private int approach1(int x) {
        boolean neg = x < 0;
        long abs = Math.abs(x);

        long rev = 0;
        while (abs > 0) {
            rev = (rev * 10 + abs%10);
            if (rev > Integer.MAX_VALUE) return 0; //+ve overflow.
            abs /= 10;
        }

        if (neg) return -1*(int)rev;
        return (int)rev;
    }
}
