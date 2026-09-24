package lc_potd;

//@link - https://leetcode.com/problems/smallest-index-with-digit-sum-equal-to-index/?
public class SmallestIndexWithDigitSumEqualToIndex {
    public int smallestIndex(int[] nums) {
        return brute(nums);
    }

    private int brute(int[] nums) {
        int n = nums.length;
        for (int i=0; i<n; i++) {
            if (i == sum(nums[i])) return i;
        }

        return -1;
    }

    private int sum(int num) {
        int s = 0;
        while (num > 0) {
            s += (num%10);
            num /= 10;
        }

        return s;
    }
}
