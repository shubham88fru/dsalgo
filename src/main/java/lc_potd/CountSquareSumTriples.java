package lc_potd;

//@link - https://leetcode.com/problems/count-square-sum-triples/description/?
public class CountSquareSumTriples {
    public int countTriples(int n) {
        // return n3(n);
        return n2(n);
    }

    private int n2(int n) {
        int count = 0;
        for (int a=1; a<=n; a++) {
            for (int b=1; b<=n; b++) {
                double c = Math.sqrt(a*a + b*b);
                if (isInt(c) && c <= n) count += 1;
            }
        }

        return count;
    }

    private boolean isInt(double num) {
        int inum = (int) num;
        return num == inum;
    }

    private int n3(int n) {
        int count = 0;
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=n; j++) {
                for (int k=1; k<=n; k++) {
                    if ((i*i + j*j == k*k)) count += 1;
                }
            }
        }

        return count;
    }
}
