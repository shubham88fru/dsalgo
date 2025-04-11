package lc_potd;

//@link -  https://leetcode.com/problems/count-symmetric-integers/
//@check - https://www.youtube.com/watch?v=abaS9eWoRkw&t=603s&ab_channel=codestorywithMIK
public class CountSymmetricIntegers {
    public int countSymmetricIntegers(int low, int high) {
        // return brute(low, high);
        return optimal(low, high);
    }

    //TC: O(n)
    //took hint from mik.
    private int optimal(int low, int high) {
        //Since the constraints say that high limit is 10^4,
        //we know that max digits can be 5. But since we are
        //only interested in even length digits, we only care
        //for numbers with 2 or 4 digits.
        int count = 0;
        for (int i=low; i<=high; i++) {
            if (i >= 10 && i < 100 && i%11 == 0) count += 1; //only those 2 dig number that are divisible by 11 are symmetric.
            else if (i >= 1000 && i <= 9999) {
                int f = i/1000;
                int s = (i/100)%10;
                int t = (i/10)%10;
                int fourth = i%10;

                if (f+s == t+fourth) count += 1;
            }
        }

        return count;
    }

    //TC: O(n*4)
    private int brute(int low, int high) {
        int count = 0;
        for (int i=low; i<=high; i++) {
            if (isSymmetric(i)) count += 1;
        }

        return count;
    }

    private boolean isSymmetric(int num) {
        int digs = (int)(Math.log10(num)+1);
        if (digs%2 != 0) return false;
        int fSum = 0;
        int sSum = 0;
        int i = 0;
        while (num > 0) {

            if (i < digs/2) {
                sSum += num%10;
            } else {
                fSum += num%10;
            }
            num /= 10;
            i += 1;
        }

        return fSum == sSum;
    }
}
