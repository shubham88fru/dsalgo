package lc_potd;

//@link - https://leetcode.com/problems/count-hills-and-valleys-in-an-array/editorial/
public class CountHillsAndValleysInAnArray {
    public int countHillValley(int[] nums) {
        return revise(nums);
    }

    /*
    * My O(~n) soln. But with extra space.
    * Mik showed a O(n) with O(1) soln
    * Check if this is a recurring problem.
    * */
    private int revise(int[] nums) {
        int n = nums.length;

        int[] pd = new int[n];
        pd[0] = -1;

        int[] sd = new int[n];
        sd[n-1] = -1;

        for (int i=1; i<n; i++) {
            if (nums[i] != nums[i-1]) pd[i] = nums[i-1];
            else pd[i] = pd[i-1];

            if (nums[n-1-i] != nums[n-i]) sd[n-1-i] = nums[n-i];
            else sd[n-1-i] = sd[n-i];
        }


        int hv = 0;

        for (int i=1; i<n-1; i++) {
            if ((nums[i] == nums[i-1]) || pd[i] == -1 || sd[i] == -1) continue;
            if (
                    (nums[i] > pd[i] && nums[i] > sd[i]) ||
                            (nums[i] < pd[i] && nums[i] < sd[i])
            ) hv += 1;
        }

        return hv;
    }
}
