package lc_potd;

//@link - https://leetcode.com/problems/minimum-number-of-operations-to-make-all-array-elements-equal-to-1/?
//@check - https://www.youtube.com/watch?v=6VuCJCcpcZI
public class MinimumNumberOfOperationsToMakeAllArrayElementsEqualTo1 {
    public int minOperations(int[] nums) {
        return editorial(nums);
    }

    private int editorial(int[] nums) {
        int n = nums.length;

        int oneCount = 0;
        for (int num: nums) {
            if (num == 1) oneCount += 1;
        }

        if (oneCount > 0) return n-oneCount;

        int opsToGet1 = Integer.MAX_VALUE;
        for (int i=0; i<n; i++) {
            int currGcd = 0;
            for (int j=i; j<n; j++) {
                currGcd = gcd(currGcd, nums[j]);
                if (currGcd == 1) {
                    opsToGet1 = Math.min(opsToGet1, j-i);
                    break;
                }
            }
        }

        return opsToGet1 == Integer.MAX_VALUE ? -1: opsToGet1 + n - 1;
    }

    public int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}
