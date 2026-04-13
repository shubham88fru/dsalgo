package lc_potd;

//@link - https://leetcode.com/problems/minimum-distance-to-the-target-element/?
public class MinimumDistanceToTheTargetElement {
    public int getMinDistance(int[] nums, int target, int start) {
        return revise(nums, target, start);
    }

    private int revise(int[] nums, int target, int start) {
        int n = nums.length;
        int abs = Integer.MAX_VALUE;
        for (int i=0; i<n; i++) {
            if (nums[i] == target) {
                abs = Math.min(abs, Math.abs(i-start));
            }
        }

        return abs;
    }
}
