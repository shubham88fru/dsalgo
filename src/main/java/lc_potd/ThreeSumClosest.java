package lc_potd;

import java.util.Arrays;

//@link - https://leetcode.com/problems/3sum-closest/description/
public class ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        // return brute(nums, target);
        return optimal(nums, target);
    }

    /**

         Core idea of the brute force solution
         implemented using the optimal
         approach of the 3 sum problem.

     */
    private int optimal(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);

        int i = 0;
        int absClosest = Integer.MAX_VALUE;
        int ans = 0;
        while (i < n) {
            if (i > 0 && nums[i] == nums[i-1]) {
                i += 1;
                continue;
            }

            int j = i + 1;
            int k = n-1;
            int tg = target - nums[i];

            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (Math.abs(sum-target) < absClosest) {
                    absClosest = Math.abs(sum-target);
                    ans = sum;
                }

                if (nums[j] + nums[k] < tg) {
                    j += 1;
                } else if (nums[j] + nums[k] > tg) {
                    k -= 1;
                } else {
                    while (j < k && nums[j] == nums[j-1]) j += 1;
                    while (k > j && nums[k] == nums[k-1]) k -= 1;

                    j += 1;
                    k -= 1;
                }
            }

            i += 1;
        }

        return ans;
    }

    private int brute(int[] nums, int target) {
        int n = nums.length;
        int absClosest = Integer.MAX_VALUE;
        int rawClosest = 0;
        for (int i=0; i<n; i++) {
            for (int j=i+1; j<n; j++) {
                for (int k=j+1; k<n; k++) {
                    int sum = nums[i] + nums[j] + nums[k];
                    if (Math.abs(sum-target) < absClosest) {
                        absClosest = Math.abs(sum-target);
                        rawClosest = sum;
                    }
                }
            }
        }

        return rawClosest;
    }
}
