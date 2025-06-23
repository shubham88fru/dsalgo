package lc_potd;

//@link - https://leetcode.com/problems/sum-of-k-mirror-numbers/description/
//@check - https://www.youtube.com/watch?v=j_cFgoSJqDQ&ab_channel=codestorywithMIK
public class SumOfKMirrorNumbers {
    public long kMirror(int k, int n) {
        return mikssol(k, n);
    }

    /*
    * Coded by me but completely
    * based on mik's explanation.
    * */
    private long mikssol(int k, int n) {
        int len = 1; //len of palindrome.
        long sum = 0;

        while (n > 0) {
            int ll = (len + 1)/2;
            int min = (int)Math.pow(10.0, ll-1);
            int max = (int)Math.pow(10.0, ll) - 1;

            for (int num=min; num<=max; num += 1) {
                String leftHalf = String.valueOf(num);
                String rightHalf = (new StringBuffer(leftHalf)).reverse().toString();

                String decimalPalindrome = "";
                if (len%2 == 0) {
                    decimalPalindrome = leftHalf + rightHalf;
                } else {
                    decimalPalindrome = leftHalf + rightHalf.substring(1, rightHalf.length());
                }

                if (isPalindrome(toBaseK(decimalPalindrome, k))) {
                    n -= 1;
                    sum += Long.parseLong(decimalPalindrome);
                    if (n==0) {
                        return sum;
                    }
                }
            }
            len += 1;
        }

        return sum;
    }

    private boolean isPalindrome(String num) {
        int l = 0;
        int r = num.length()-1;

        while (l <= r) {
            if (num.charAt(l) != num.charAt(r)) return false;
            l += 1;
            r -= 1;
        }

        return true;
    }

    private String toBaseK(String decimal, int k) {
        long dec = Long.parseLong(decimal);
        StringBuilder sb = new StringBuilder();

        while (dec > 0) {
            sb.append(dec%k);
            dec /= k;
        }

        return sb.reverse().toString();
    }
}
