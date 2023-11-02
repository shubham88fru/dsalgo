package ptrn.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * For optimal solution @see strvr.stacksandqueues#SlidingWindowMaximum
 */
public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        //return getMaxInEachWindow(nums, k);
        return slidingWindow(nums, k);
    }

    //2) Better soln using sliding window.
    private int[] slidingWindow(int[] nums, int k) {
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
