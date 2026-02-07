package lc_potd;

//@link - https://leetcode.com/problems/remove-element/?
public class RemoveElement {
    public int removeElement(int[] nums, int val) {
        return optimal(nums, val);
    }

    private int optimal(int[] nums, int val) {
        int n = nums.length;

        int j=0;
        for (int i=0; i<n; i++) {
            if (nums[i] != val) {
                nums[j] = nums[i];
                j += 1;
            }
        }

        return j;
    }
}
