package lc_potd;

//@link - https://leetcode.com/problems/maximum-product-of-two-elements-in-an-array/?
public class MaximumProductOfTwoElementsInAnArray {
    public int maxProduct(int[] nums) {
        return revise(nums);
    }

    private int revise(int[] nums) {
        int n = nums.length;
        int m = Integer.MIN_VALUE, sm = Integer.MIN_VALUE;
        for (int num: nums) {
            if (num >= m) {
                sm = m;
                m = num;
            } else if (num > sm) {
                sm = num;
            }
        }

        return (m-1)*(sm-1);
    }
}
