package lc_potd;

//@link - https://leetcode.com/problems/count-operations-to-obtain-zero/?
//@check - https://www.youtube.com/watch?v=1Vq8EjnWTNE
public class CountOperationsToObtainZero {
    public int countOperations(int num1, int num2) {
        // return revise(num1, num2);
        return optimal(num1, num2);
    }

    /**
         Optimal.
         This is basically euclidean algo
         a.k.a GCD
     */
    private int optimal(int num1, int num2) {

        int ops = 0;

        /**
             Note that we don't necessarily need
             to guarantee that num1 > num2 at the start
             because if it is not, num1/num2 will be 0,
             so we won't be adding any ops and num1%num2 will
             be num1 only. Furthermore, we'll anyway end up swapping
             num1 and num2 and from next iteration, things will follow
             properly.
         */
        while (num1 > 0 && num2 > 0) {

            ops += num1/num2;
            num1 %= num2;

            //Now, num2 will definitely be larger
            //so swap to make num1 = num2 and num2 = num1

            //Nice trick to swap two nums without
            //using a third variable. There's
            //a xor based approach as well
            //which is even better.
            num1 = num1 + num2; //a = a + b
            num2 = num1 - num2; //a = a - b
            num1 = num1 - num2;
        }

        return ops;
    }

    /**
     Brute.
     */
    private int revise(int num1, int num2) {
        int ops = 0;

        //Interesting that it is guaranteed
        //that at least one of the nums will
        //become zero by continuously doing this
        //operation.
        while (num1 != 0 && num2 != 0) {
            if (num1 >= num2) num1 -= num2;
            else num2 -= num1;
            ops += 1;
        }

        return ops;
    }
}
