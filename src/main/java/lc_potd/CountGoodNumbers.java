package lc_potd;

//@link - https://leetcode.com/problems/count-good-numbers/description/
public class CountGoodNumbers {
    public int countGoodNumbers(long n) {
        // return pass1(n);
        // return (pass2(n));
        return pass3(n);
    }

    //3 - My soln using binary expo.
    private int pass3(long n) {
        if (n%2==0) {
            return (int)((binaryExp(5, n/2) * binaryExp(4, n/2) )%1000000007L);
        }

        return (int)((binaryExp(5, n/2 + 1) * binaryExp(4, n/2) )%1000000007L);
    }

    public long binaryExp(long base,long expo){
        long ans = 1;
        while(expo>0)
        {
            if(expo%2==0)
            {
                base = (base*base)%1000000007L;
                expo = expo/2;
            }
            else{
                ans = (ans*base)%1000000007L;
                expo-=1;
            }
        }
        return ans;
    }

    /*
        2.
        Simple Math.pow will blow up the limits
        coz the powers could be very large.
        Therefore, we need a way to tap
        into the power calculation and apply
        mod at appropriate places so as to not
        blow up the limits.
    */
    private int pass2(long n) {

        if (n%2==0) {
            return (int)((Math.pow(5, n/2) * Math.pow(4, n/2) )%1000000007d);
        }

        return (int)((Math.pow(5, n/2 + 1) * Math.pow(4, n/2) )%1000000007d);
    }

    /*
        1.
        Since recursive stack will be too deep,
        this will blow up the stack, because n
        is huge.
    */

    private int pass1(long n) {
        return (int)recursion(n, 0);
    }

    private long recursion(long n, long i) {
        if (i >= n) return 1;

        long count = 0;
        if (i%2 == 0) {
            count += (5 * recursion(n, i+1l)%1000000007);
        } else {
            count += (4 * recursion(n, i+1l)%1000000007);
        }

        return count;
    }
}
