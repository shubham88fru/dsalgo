package lc_potd;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/find-the-power-of-k-size-subarrays-i/description/
public class FindThePowerOfKSizeSubarraysI {
    public int[] resultsArray(int[] nums, int k) {
        // return brute(nums, k);
        return optimal(nums, k);
    }

    /*
        My soln. Mik also solve using sliding window
        but with a slightly different approach, maybe more cleaner.
     */
    private int[] optimal(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n-k+1];
        Map<Integer, Integer> mp = new HashMap<>();

        //edge case - when k is 1, og array is the ans.
        if (k == 1) return nums;

        int l = 0;
        int r = 0;
        int idx = 0;
        //sliding window.
        while (r < n) {
            if (r-l+1 <= k) {
                if (r != 0) {
                    int diff = nums[r]-nums[r-1];
                    mp.put(diff, mp.getOrDefault(diff, 0)+1);
                }
                r += 1;
            } else {
                //if the window has just one difference and that
                //too is 1, means all elments of the window are
                //consecutive.
                if ((mp.size()==1 && mp.containsKey(1))) {
                    ans[idx] = nums[r-1];
                } else {
                    ans[idx] = -1;
                }

                int release = nums[l];
                int releaseDiff = nums[l+1]-nums[l];
                mp.put(releaseDiff, mp.get(releaseDiff)-1);
                if (mp.get(releaseDiff) == 0) mp.remove(releaseDiff);

                l += 1;
                idx += 1;
            }
        }

        /*
            This always happens to me in sliding window problems,
            I somehow end up missing the last window, and have to
            write the extra code to account for the last window.
            Need to do something about this.
        */
        if (mp.size() == 0 || (mp.size()==1 && mp.containsKey(1))) {
            ans[idx] = nums[r-1];
        } else {
            ans[idx] = -1;
        }

        return ans;
    }

    //brute force works - lol
    private int[] brute(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n-k+1];

        for (int i=0; i<n-k+1; i++) {
            int prev = -1;
            for (int j=i; j<i+k; j++) {
                if (prev == -1) {
                    prev = nums[j];
                    continue;
                } else if (nums[j]-prev != 1) {
                    ans[i] = -1;
                    break;
                } else {
                    prev = nums[j];
                }
            }
            if (ans[i] != -1) ans[i] = prev;
        }

        return ans;
    }
}
