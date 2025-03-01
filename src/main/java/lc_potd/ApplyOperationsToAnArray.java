package lc_potd;

//@link - https://leetcode.com/problems/apply-operations-to-an-array/
//@check - https://www.youtube.com/watch?v=K0FuadqyBHs&t=940s&ab_channel=codestorywithMIK
public class ApplyOperationsToAnArray {
    public int[] applyOperations(int[] nums) {
        // return pass1(nums);
        return pass2(nums);
    }

    //1) Mik's one pass approach.
    private int[] pass2(int[] nums) {
        int n = nums.length;

        int j=0;
        for (int i=0; i<n; i++) {
            if (i != n-1 && nums[i+1] == nums[i]) {
                nums[i+1] = 0;
                nums[i] = 2*nums[i];
            }

            if (nums[i] != 0 && i != j) {
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
                j += 1;
            }

            if (nums[i] != 0) {
                j += 1;
            }
        }

        return nums;
    }

    //2) My two pass soln.
    private int[] pass1(int[] nums) {
        int n = nums.length;

        //simulate
        for (int i=0; i<n-1; i++) {
            if (nums[i+1] == nums[i]) {
                nums[i+1] = 0;
                nums[i] = 2*nums[i];
            }
        }

        //move all zeros to end.
        int zi = -1;
        int i=0;
        while (i < n) {
            if (nums[i] != 0) {
                int temp = nums[zi+1];
                nums[zi+1] = nums[i];
                nums[i] = temp;
                zi += 1;
            }
            i +=1;
        }
        return nums;
    }
}
