package lc_potd;

//@link - https://leetcode.com/problems/count-equal-and-divisible-pairs-in-an-array/
public class CountEqualAndDivisiblePairsInAnArray {
    public int countPairs(int[] nums, int k) {
        return brute(nums, k);
    }

    /*
    * This isn't the optimal sln ofc.
    * Mik showed a soln using gcd, but was very confusing
    * and felt like and overkill.
    * */
    private int brute(int[] nums, int k) {
        int count = 0;
        for (int i=0; i<nums.length; i++) {
            for (int j=i+1; j<nums.length; j++) {
                if (nums[i] == nums[j] && (i*j)%k==0) count += 1;
            }
        }

        return count;
    }
}
