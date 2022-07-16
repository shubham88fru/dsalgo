package basics.recursion;

//Find value of fibonacci series
//at nth index.
//n>=0
public class Fibonacci {

    static int fib(int n) {
        // if (n<=1) return n;
        if (n==0) return 0;
        if (n==1) return 1;

        //0 1 1 2 3 5 8 13
        return fib(n-1) + fib(n-2);
    }

    public static void main(String[] args) {
        System.out.println(fib(7));
    }
}
