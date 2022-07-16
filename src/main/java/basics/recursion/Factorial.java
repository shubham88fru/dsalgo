package basics.recursion;

public class Factorial {

    static int factorial(int n) {
        if  (n==1 || n==0) return 1;
        /*
         * Even though it appears that since
         * recursive call is at the last line,
         * it is tail recursive, but it is not.
         * Because recursive call is not the last
         * thing that happens here. During execution
         * jvm will have to wait for recursive call to
         * return and then multiply it with `n`.
         */
        return n*factorial(n-1);
    }

    static int factorialTailRecursive(int n, int k) {
        if (n==1 || n==0) return k;
        return factorialTailRecursive(n-1, k*n);
    }

    public static void main(String[] args) {
        System.out.println(factorial(2));
        System.out.println(factorial(3));
        System.out.println(factorial(4));

        System.out.println("---------------------");

        System.out.println(factorialTailRecursive(2, 1));
        System.out.println(factorialTailRecursive(3, 1));
        System.out.println(factorialTailRecursive(4, 1));
    }
}
