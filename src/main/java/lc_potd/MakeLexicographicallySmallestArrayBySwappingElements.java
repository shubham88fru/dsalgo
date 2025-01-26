package lc_potd;

import java.util.*;

//@link - https://leetcode.com/problems/make-lexicographically-smallest-array-by-swapping-elements/
//@check - https://www.youtube.com/watch?v=_rQ4lJlI6nI&t=2443s&ab_channel=codestorywithMIK
public class MakeLexicographicallySmallestArrayBySwappingElements {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        // return pass1(nums, limit);
        // return pass2(nums, limit);
        return pass3(nums, limit);
    }

    /*
        This is the optimal solution, as per mik's
        explanation. There is no way on earth, I can
        ever come up with this approach myself :(

        However, I think using DSU this problem can
        be solved more cleanly. If this is a recurring
        problem for some company, try other solutions
        online.
    */
    private int[] pass3(int[] nums, int limit) {
        int[] og = new int[nums.length];
        for (int i=0; i<nums.length; i++) og[i] = nums[i];

        Arrays.sort(nums);
        List<TreeMap<Integer, Integer>> lst = new ArrayList<>();

        TreeMap<Integer, Integer> grp = new TreeMap<>();
        grp.put(nums[0], 1);

        int grpIdx = 0;
        int[] ans = new int[nums.length];
        // ans[0] = 0;
        Map<Integer, Integer> groupIndex = new HashMap<>();
        groupIndex.put(nums[0], 0);


        for (int i=1; i<nums.length; i++){
            if (Math.abs(nums[i]-nums[i-1]) <= limit) {
                grp.put(nums[i], grp.getOrDefault(nums[i], 0)+1);
            } else {
                lst.add(grp);
                grpIdx += 1;
                grp = new TreeMap<>();
                grp.put(nums[i], 1);
            }
            // ans[i] = grpIdx;
            groupIndex.put(nums[i], grpIdx);
        }

        lst.add(grp);

        for (int i=0; i<nums.length; i++) {
            TreeMap<Integer, Integer> mp = lst.get(groupIndex.get(og[i]));
            int sm = mp.firstKey();
            mp.put(sm, mp.get(sm)-1);
            if (mp.get(sm) == 0) mp.remove(sm);
            ans[i] = sm;
        }

        return ans;
    }

    /*
        My brute force approach.
        Will give TLE.
    */
    private int[] pass2(int[] nums, int limit) {

        int n = nums.length;
        for (int i=0; i<n; i++) {
            int j = i + 1;
            while (j < n) {
                if (Math.abs(nums[j]-nums[i]) <= limit && nums[j] < nums[i]) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                    j = i + 1;
                    continue;
                }

                j += 1;
            }
        }

        return nums;
    }

    /*
        Based on my dry run, I thought using a custom comparator
        will solve this problem. However, this approach failed
        for some test cases -
        nums: [1,60,34,84,62,56,39,76,49,38]
        limit: 4

        I'm not sure why though. The comparator
        wasn't sorting the numbers the way I expected
        it to.
    */
    private int[] pass1(int[] nums, int limit) {
        int n = nums.length;
        Comparator<Integer> cmp1 = (i1, i2) -> {

            if (Math.abs(i1-i2) <= limit) {
                if (i1 < i2) return -1;
                else if (i1 > i2) return 1;
                else return 0;
            }

            return 0;
        };

        return Arrays.stream(nums)
                .boxed().sorted(cmp1)
                .mapToInt(i->i)
                .toArray();
    }
}
