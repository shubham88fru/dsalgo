package lc_potd;

//@link - https://leetcode.com/problems/minimum-value-to-get-positive-step-by-step-sum/?
public class MinimumValueToGetPositiveStepByStepSum {
    public int minStartValue(int[] nums) {
        return revise(nums);
    }

    private int revise(int[] nums) {
        int mp = 1;

        int rs = 0;
        for (int n: nums) {
            rs += n;
            if (rs < 0 && mp+rs < 1) {
                mp = Math.abs(rs) + 1;
            }
        }

        return mp;
    }
}
