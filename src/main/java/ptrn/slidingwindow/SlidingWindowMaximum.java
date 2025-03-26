package ptrn.slidingwindow;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * For optimal solution @see {@link strvr.stacksandqueues.SlidingWindowMaximum}
 */
//@link - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6355320395005952
public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        //return getMaxInEachWindow(nums, k);
        return slidingWindow1(nums, k);
    }

    //1) My revision soln using treemap.
    private int[] slidingWindow1(int[] nums, int k) {
        int n = nums.length;

        int[] ans = new int[n-k+1];
        int l = 0;
        int r = 0;
        TreeMap<Integer, Integer> window = new TreeMap<>();
        int i = 0;
        while (r < n) {
            while (r-l+1<=k) {
                window.put(nums[r], window.getOrDefault(nums[r], 0)+1);
                r += 1;
            }

            ans[i] = window.lastKey();
            i += 1;
            int rem = nums[l];
            window.put(rem, window.get(rem)-1);
            if (window.get(rem) == 0) window.remove(rem);
            l += 1;
        }

        return ans;
    }

    private int[] slidingWindow2(int[] nums, int k) {
        int n = nums.length;

        int[] ans = new int[n-k+1];
        int l = 0;
        int r = 0;
        TreeMap<Integer, Integer> window = new TreeMap<>();
        int i = 0;
        while (r < n) {
            int num = nums[r];
            if (r - l + 1 <= k) {
                window.put(num, window.getOrDefault(num, 0)+1);
                r += 1;
            } else {
                ans[i] = window.lastKey();
                i += 1;
                int rem = nums[l];
                window.put(rem, window.get(rem)-1);
                if (window.get(rem) == 0) window.remove(rem);
                l += 1;
            }
        }

        ans[i] = window.lastKey();
        i += 1;

        return ans;
    }

    //2) Better soln using sliding window.
    private int[] slidingWindow3(int[] nums, int k) {
        //start of window pointers.
        int left = 0;
        int right = 0;
        int n = nums.length;

        int[] ans = new int[n-k+1];
        Map<Integer, Integer> mp = new HashMap<>();
        int maxInWindow = Integer.MIN_VALUE;
        int windowNum = 0;
        while (right < nums.length) {
            //when within the first window,
            //keep acquiring and comparing to keep track of maximum.
            if (right < k) {
                if (nums[right] > maxInWindow) maxInWindow = nums[right];
                ans[windowNum] = maxInWindow; //notice we won't increase window count here. We're still in first window.
            } else { //next window, need to release left and acquire right.
                //to be acquired.
                int toAdd = nums[right];

                //to be released.
                int toRemove = nums[left];
                left += 1;
                mp.put(toRemove, mp.get(toRemove)-1);

                //if the released item's frequency becomes zero,
                //then we need to be careful because it could have been our
                //max element - we'll need a new maximum in that case.
                if (mp.get(toRemove) == 0) {
                    //if indeed it was max - reset the max of the window and find a new max.
                    if (maxInWindow == toRemove) {
                        maxInWindow = Integer.MIN_VALUE;
                        for (int i=left; i<=right; i++) {
                            if (nums[i] > maxInWindow) maxInWindow = nums[i];
                        }
                    } else { //otherwise, we still have the max element somewehre in the window.
                        //just see, if the acquired element is greater than max. If yes, we have a new max.
                        if (toAdd > maxInWindow) maxInWindow = toAdd;
                    }
                    //after handling the case when freq becomes zero, remove the
                    //0 freq element from the map entirely.
                    mp.remove(toRemove);
                } else { //otherwise, we still have the max element somewehre in the window.
                    //just see, if the acquired element is greater than max. If yes, we have a new max.
                    if (toAdd > maxInWindow) maxInWindow = toAdd;
                }

                //record the max for next window.
                ans[++windowNum] = maxInWindow;
            }

            //put the acquired element to map.
            mp.put(nums[right], mp.getOrDefault(nums[right], 0)+1);

            //increse the right.
            right += 1;
        }
        return ans;
    }
}
