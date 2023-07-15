package strvr.arrays3;

//@link - https://leetcode.com/problems/powx-n/description/
//@strvr - https://takeuforward.org/data-structure/implement-powxn-x-raised-to-the-power-n/
public class PowerOfN {
    public double myPow(double x, int n) {
        //return powerBruteIterative(x, n);
        //return powerBruteRecursive(x, n);
        return raiseToOptimal(x, n);
    }

    //1) Optimal solution: T: O(logN), S: O(1)
    private double raiseToOptimal(double x, int n) {
        double ans = 1.0;

        //calculate the positive power and
        //we'll just invert the answer to
        //handle the negative case.
        double nn = n > 0 ? n: (-1.0)*n;
        while (nn > 0) {
            //when power is even,
            //x = x*x and halve the power
            //eg: 2 ^ 10 --> (2 * 2) ^ 5
            if (nn%2 == 0) {
                x = x * x;
                nn = nn/2.0;
            } else {
                //else when power is odd
                //eg: 4 ^ 5 --> 4 * (4 ^ 4)
                ans = ans * x;
                nn = nn - 1;
            }
        }

        //Invert the answer if power is -ve.
        if (n < 0) ans = 1.0/ans;
        return ans;

    }

    //2) Brute force solutions.
    private double powerBruteRecursive(double x, int n) {
        if (n == 0.0) return 1.0;
        if (n > 0) return (x * powerBruteRecursive(x, n-1));
        else return ((1.0/x) * powerBruteRecursive(x, n+1));
    }

    private double powerBruteIterative(double x, int n) {
        if (n == 0) return 1.0;

        double ans = 1.0;
        if (n > 0) {
            while (n > 0) {
                ans *= x;
                n -= 1;
            }
        } else {
            while (n < 0) {
                ans *= (1.0/x);
                n += 1;
            }
        }

        return ans;
    }
}
