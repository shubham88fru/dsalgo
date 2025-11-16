package lc_potd;

//@link - https://leetcode.com/problems/number-of-substrings-with-only-1s/description/
public class NumberOfSubstringsWithOnly1s {
    public int numSub(String s) {
        // return optimal(s);
        return optimal2(s);
    }

    /**
     This is even better than
     my other soln. Don't have to
     deal with multiplications etc.
     */
    private int optimal2(String s) {
        int n = s.length();

        int l = 0;
        int r = 0;

        long ans = 0;
        final int MOD = 1000000007;
        while (r < n) {

            while (r < n && s.charAt(r) == '1') {
                r += 1;
                ans += (r-l); //sum of natural nums
            }

            r += 1;
            l = r;
        }

        return (int)(ans%MOD);
    }

    /**
     I wrote the sliding window solution
     because that was my first intuition.
     However, this is absolutely unnecessary
     and might even hurt more than do any
     good during an interview.
     */
    private int optimal(String s) {
        int n = s.length();

        int l = 0;
        int r = 0;

        long ans = 0;
        final int MOD = 1000000007;
        while (r < n) {

            while (r < n && s.charAt(r) == '1') {
                r += 1;
            }

            if (s.charAt(l) == '1') {
                long m = r-l;
                long sum = m*(m+1)/2; //sum of natural nums.
                ans = (ans + sum);
            }

            r += 1;
            l = r;
        }

        return (int)(ans%MOD);
    }
}
