package lc_potd;

//@link - https://leetcode.com/problems/find-minimum-operations-to-make-all-elements-divisible-by-three/description/?
public class FindMinimumOperationsToMakeAllElementsDivisibleByThree {
    public int minimumOperations(int[] nums) {
        // return brute(nums);
        return optimal(nums);
    }

    private int optimal(int[] nums) {
        int ops = 0;
        for (int i=0; i<nums.length; i++) {
            if (nums[i]%3 != 0) ops += 1; //either +1 will make it divisible by 3 or -1 will.
        }

        return ops;
    }

    private int brute(int[] nums) {
        int n = nums.length;

        int ops = 0;
        for (int num: nums) {
            if (num%3 == 0) continue;

            int subOps = 1;
            for (int i=1; i<=2; i++) {
                if ((num-i)%3 == 0) break;
                subOps += 1;
            }

            int addOps = 1;
            for (int i=1; i<=2; i++) {
                if ((num+i)%3 == 0) break;
                addOps += 1;
            }

            ops += Math.min(subOps, addOps);
        }

        return ops;
    }
}
