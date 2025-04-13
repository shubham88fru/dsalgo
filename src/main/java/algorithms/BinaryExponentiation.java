package algorithms;

/*
* a.k.a - Fast exponentiation.
* It's an efficient way to calculate large powers.
* Useful when the exponent is very large.
* TC: O(log(b)) where b is the exponent.
* Note that the typical/naive way to calculate
* powers has a TC of O(b) where the idea is
* to keep multiplying the base b times.
*
*
* key concept -
* a^b = (a^(b/2))^2     -- when b is even.
*     = a*(a^(b/2))^2   -- when b is odd.
*
* */
//@check - https://www.youtube.com/watch?v=D320QeHS0XQ&t=618s&ab_channel=codestorywithMIK
public class BinaryExponentiation {

    //1) Recursive approach.
    private long beRecursive(long a, long b) {
        if (b == 0) {
            return 1;
        }

        long half = beRecursive(a, b / 2);
        long result = half * half;
        if (b % 2 == 1) {
            result *= a;
        }

        return result;
    }

    //2) Iterative.
    private long beIterative(long base, long expo) {
        long ans = 1;
        while(expo>0) {
            if (expo%2 == 0) {
                base = (base*base);
                expo = expo/2;
            } else {
                ans = (ans*base);
                expo-=1;
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        BinaryExponentiation be = new BinaryExponentiation();
        System.out.println(be.beRecursive(2l, 2l));
        System.out.println(be.beRecursive(2l, 10l));

        System.out.println(be.beIterative(2l, 2l));
        System.out.println(be.beIterative(2l, 10l));
    }
}
