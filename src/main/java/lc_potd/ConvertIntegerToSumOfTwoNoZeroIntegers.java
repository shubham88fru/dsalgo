package lc_potd;

//@link - https://leetcode.com/problems/convert-integer-to-the-sum-of-two-no-zero-integers/description/
public class ConvertIntegerToSumOfTwoNoZeroIntegers {
    public int[] getNoZeroIntegers(int n) {
        return revise(n);
    }

    /*
        This is a O(n*log(n)) solution.
        Mik showed a O(log(n)) soln as well.
     */
    private int[] revise(int n) {

        /*
            This won't work.
            e.g. n = 906
        */
        //if ((n-1)%10 == 0) return new int[]{n-2, 2};
        //return new int[]{n-1, 1};

        int a = 1;
        int b = n-1;

        while (true) {
            if (b==0) return null;
            if (nozero(a) && nozero(b)) return new int[]{a, b};

            a += 1;
            b -= 1;
        }
    }

    private boolean nozero(int a) {
        while (a > 0) {
            if (a%10 == 0) return false;
            a /= 10;
        }

        return true;
    }
}
