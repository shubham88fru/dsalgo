package recursion;

/*
Given a rope of length n, you need to find maximum number of
pieces you can make such that length of every piece is in set {a, b, c}
for given three values a, b and c.
* */
public class Problem1 {

    //T: O(3^n)
    //Not efficient, better soln using dp is possible.
    static int maxCuts(int n, int a, int b, int c) {
        if (n==0) return 0;
        if (n<0) return -1;

        int res = Math.max(Math.max(maxCuts(n - a, a, b, c),
                        maxCuts(n - b, a, b, c)),
                maxCuts(n - c, a, b, c));
        if (res == -1) return -1;
        return 1+res;
    }

    public static void main(String[] args) {
        System.out.println(maxCuts(5, 2,1,5));
        System.out.println(maxCuts(23, 11,9,12));
    }
}
