package hellointerview;

//@link - https://www.hellointerview.com/learn/code/two-pointers/move-zeroes
public class MoveZeroes {
    public void moveZeroes(int[] nums) {
        revise(nums);
    }

    private void revise(int[] nums) {
        int j = 0;
        for (int i=0; i<nums.length; i++) {
            if (nums[i] != 0) {
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
                j += 1;
            }
        }
    }
}
