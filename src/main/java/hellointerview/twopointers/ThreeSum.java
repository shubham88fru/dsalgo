package hellointerview.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@link - https://www.hellointerview.com/learn/code/two-pointers/3-sum
public class ThreeSum {
    private List<List<Integer>> optimal(int[] nums) {
        int n = nums.length;

        Arrays.sort(nums);

        List<List<Integer>> ans = new ArrayList<>();
        int i=0;
        while (i < n) {
            if (i > 0 && nums[i] == nums[i-1]) {
                i += 1;
                continue;
            }

            int tg = -nums[i];
            int j=i+1, k=n-1;
            while (j < k) {
                if (nums[j] + nums[k] < tg) {
                    j += 1;
                } else if (nums[j] + nums[k] > tg) {
                    k -= 1;
                } else {
                    ans.add(List.of(nums[i], nums[j], nums[k]));
                    j += 1;
                    k -= 1;

                    while (j < k && nums[j] == nums[j-1]) j += 1;
                    while (j < k && nums[k] == nums[k+1]) k -= 1;
                }
            }

            i += 1;
        }

        return ans;
    }
}
