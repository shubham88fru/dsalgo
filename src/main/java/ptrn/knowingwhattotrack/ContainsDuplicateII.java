package ptrn.knowingwhattotrack;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/contains-duplicate-ii/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5527127085809664
public class ContainsDuplicateII {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        // return slidingWindow(nums, k);
        return simpleHashMap(nums, k);
    }


    private boolean simpleHashMap(int[] nums, int k) {
        Map<Integer, Integer> mp = new HashMap<>();
        for (int i=0; i<nums.length; i++) {
            int num = nums[i];
            if (mp.containsKey(num)) {
                //If the num is found in map,
                //check if it follows the condition, if
                //not update the map with latest occurrence,
                //coz we care about the latest position since
                //we have to find `<= k`.
                if (i-mp.get(num) <= k) return true;
            }

            mp.put(num, i);
        }

        return false;
    }


    private boolean slidingWindow(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> window = new HashMap<>();
        int tail = 0;
        int head = 0;

        //sliding window implementation.
        for (int i=0; i<n; i++) {
            //keep acquiring the first k
            //element, since the window is empty to start with.
            if (head < k+1) {
                int el = nums[head];
                if (!window.containsKey(el)) window.put(el, 1);
                else return true; //if at any point, we find a duplicate in curr window, return.
            } else {
                int toAdd = nums[head]; //to be acquired (i.e. put in window)
                int toLeave = nums[tail]; //to be released (i.e. removed from window.)
                if (window.containsKey(toLeave)) window.remove(toLeave); //if window has the toLeave, remove it.
                if (window.containsKey(toAdd)) return true; //if window already has toAdd, means a duplicate.
                else window.put(toAdd, 1); //otherwise, add toAdd in the windows.

                //shorten from tail
                tail += 1;
            }

            //increase from head.
            head += 1;
        }

        return false;
    }
}
