package strvr.arrays4;

import java.util.*;

//@link - https://leetcode.com/problems/4sum/description/
//@strvr - https://takeuforward.org/data-structure/4-sum-find-quads-that-add-up-to-a-target-value/
public class FourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        //return fourSumBrute(nums, target);
        return fourSumOptimal(nums, target);
    }

    //0) I think this problem can be done using
    //recursion/backtracking also. But not sure
    //if that'll give TLE. Maybe try on next encounter.

    //1) Optimal solution: T: O(N^3), S: O(1)
    //using 4 pointers
    private List<List<Integer>> fourSumOptimal(int[] nums, int target) {
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);

        //4 pointers - i, j, k, l
        for (int i=0; i<n; i++) {//pointer 1 (p1) -- starts from start of array (and moves next)
            if (i != 0 && nums[i] == nums[i-1]) continue; //skip if p1 points to same value as it did before.
            for (int j = i+1; j<n; j++) {//pointer 2 (p2) -- starts after i (and moves next)
                if (j != i+1 && nums[j] == nums[j-1]) continue; //skip if p2 points to same value as it did before.
                int k = j+1; //pointer 3 (p3) -- starts after j (and moves next)
                int l = n-1; //pointer 4 (p4) -- points at the end (moves prev)

                /*
                    Rewatch the solution maybe.
                    Didn't get why we're not using equal to symbol in below while loops.
                */
                while (k < l) {
                    long sum = (long)nums[i] + (long)nums[j] + (long)nums[k] + (long)nums[l];
                    if (sum == target) {
                        ans.add(Arrays.asList(nums[i], nums[j], nums[k], nums[l]));
                        k += 1; l -= 1;
                        while (k < l && nums[k] == nums[k-1]) k += 1;
                        while (k < l && nums[l] == nums[l+1]) l -= 1;
                    } else if (sum < target) k += 1;
                    else l -= 1;
                }
            }
        }

        return ans;
    }

    //2) Brute force: T: O(N^4), S: O(1)
    //Generate all possible qudraplets and see if the quadraplet's
    //sum is equal to the target.
    private List<List<Integer>> fourSumBrute(int[] nums, int target) {
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        Map<String, Integer> mp = new HashMap<>();

        for (int i=0; i<n; i++) {
            for (int j=i+1; j<n; j++) {
                for (int k=j+1; k<n; k++) {
                    for (int l=k+1; l<n; l++) {
                        int sum = nums[i] + nums[j] + nums[k] + nums[l];

                        //Sort the potential ans array since so that we don't
                        //store duplicates.
                        int[] arr = new int[] {nums[i], nums[j], nums[k], nums[l]};
                        Arrays.sort(arr);
                        String key = arr[0] + "_" + arr[1] + "_" + arr[2] + "_" + arr[3];

                        if (sum == target) {
                            if (!mp.containsKey(key)) {
                                ans.add(Arrays.asList(nums[i], nums[j], nums[k], nums[l]));
                                mp.put(key, 0);
                            }
                        }
                    }
                }
            }
        }

        return ans;
    }
}
