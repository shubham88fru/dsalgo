package lc_potd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@link - https://leetcode.com/problems/the-number-of-beautiful-subsets/description/
public class NumberOfBeautifulSubsets {
    public int beautifulSubsets(int[] nums, int k) {
        List<List<Integer>> subarrs = new ArrayList<>();
        subs(nums, k, 0, new ArrayList<>(), subarrs, new HashMap<>());
        return subarrs.size()-1; //return after ignoring the empty subarray.
    }

    private void subs(int[] nums, int k, int curr,
                      List<Integer> currList, List<List<Integer>> subarrs, Map<Integer, Integer> mp) {
        if (curr >= nums.length) {
            //we've completed a subset, add to record.
            subarrs.add(new ArrayList<>(currList));
            return;
        }

        //technique like two sum to find if a pairing num exists in curr subset.
        if (!mp.containsKey(k+nums[curr]) && !mp.containsKey(nums[curr]-k)) {
            //if not..
            //then pick curr num and proceed with further options.
            currList.add(nums[curr]);
            mp.put(nums[curr], mp.getOrDefault(nums[curr], 0)+1);
            subs(nums, k, curr+1, currList, subarrs, mp);

            //then backtrack.
            currList.remove(currList.size()-1);
            mp.put(nums[curr], mp.getOrDefault(nums[curr], 1)-1);
            if (mp.get(nums[curr]) <= 0) mp.remove(nums[curr]);
        }

        //not pick.
        subs(nums, k, curr+1, currList, subarrs, mp);
    }
}
