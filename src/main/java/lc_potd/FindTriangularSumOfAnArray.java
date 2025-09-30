package lc_potd;

//@link - https://leetcode.com/problems/find-triangular-sum-of-an-array/?
public class FindTriangularSumOfAnArray {
    public int triangularSum(int[] nums) {
        return pass1(nums);
    }

    private int pass1(int[] nums) {
        int n = nums.length;

        while (n > 1) {

            for (int j=0; j<n-1; j++) {
                nums[j] = (nums[j] + nums[j+1])%10;
            }
            n -= 1;
        }

        return nums[0];
    }
}
