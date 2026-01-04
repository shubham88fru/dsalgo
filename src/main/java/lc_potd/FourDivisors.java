package lc_potd;

//@link - https://leetcode.com/problems/four-divisors/description/?
public class FourDivisors {
    public int sumFourDivisors(int[] nums) {
        // return brute(nums);
        return optimal(nums);
    }

    /**
     The only thing to learn from
     this problem is basically
     how to efficiently get factors
     of a number -
     1. Only need to check till sqrt(num)
     - Important: keep collecting pairs.
     2. Beware of duplicate counting e.g. 2*2 = 4.

     The above is basically sieve of eratosthenes
     for finding the factors.

     Mik had the same soln.
     */
    private int optimal(int[] nums) {
        int ts = 0;
        for (int num: nums) {
            int d = 1;
            int count = 0;
            int sum = 0;
            while (d <= Math.sqrt(num)) {
                if (num%d == 0) {
                    if (d != num/d) count += 2;
                    else count += 1;
                    sum += (d + num/d); //d and num/d both found.
                    if (count > 4) break;
                }
                d += 1;
            }
            if (count == 4) ts += sum;
        }

        return ts;
    }

    private int brute(int[] nums) {
        int n = nums.length;
        int ts = 0;
        for (int num: nums) {
            int d = 1;
            int count = 0;
            int sum = 0;
            while (d <= num) {
                if (num%d == 0) {
                    count += 1;
                    sum += d;
                    if (count > 4) break;
                }
                d += 1;
            }
            if (count == 4) ts += sum;
        }

        return ts;
    }
}
