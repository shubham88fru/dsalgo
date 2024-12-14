package lc_potd;

import java.util.PriorityQueue;
import java.util.TreeMap;

//@link - https://leetcode.com/problems/continuous-subarrays/
//@check - https://www.youtube.com/watch?v=SWyGD8w_85E&t=1393s&ab_channel=codestorywithMIK
public class ContinuousSubarrays {
    public long continuousSubarrays(int[] nums) {
        return mysol2(nums);
    }

    //In this approach, I tried to use
    //priority queue, but wasn't working.
    private long mysol(int[] nums) {
        int n = nums.length;
        int l = 0;
        int r = 0;
        PriorityQueue<Integer> max = new PriorityQueue<>((i1, i2) -> i2-i1);
        PriorityQueue<Integer> min = new PriorityQueue<>();

        max.add(nums[0]);
        min.add(nums[0]);

        int count = 0;
        while (r < n) {
            if ((r == l) || Math.abs(max.peek()-min.peek()) <= 2) {
                count += (r-l+1);
                r += 1;

                if (r < n) {
                    max.add(nums[r]);
                    min.add(nums[r]);
                }
            } else {
                int rem = nums[l];
                if (max.peek() == rem) max.remove();
                if (min.peek() == rem) min.remove();
                l += 1;
            }
        }

        return count;
    }

    //In this approach, I used sorted map.
    //Got hint to used treemap from mik.
    private long mysol2(int[] nums) {
        int n = nums.length;
        int l = 0;
        int r = 0;

        TreeMap<Integer, Integer> tm = new TreeMap<>();
        tm.put(nums[0], 1);

        long count = 0;
        while (r < n) {
            //If in current window that max and min differ by less
            //than 2, then any other pairs would too.
            if (Math.abs(tm.firstKey()-tm.lastKey()) <= 2) {
                //I was 70% close to solving this question myself,
                //only this trick, I couldn't figure out.
                count += (long)(r-l+1); //with every acquire, we are adding r-l+1 more subarrays.
                r += 1;

                if (r < n) {
                    tm.put(nums[r], tm.getOrDefault(nums[r], 0) + 1);
                }
            } else {
                int rem = nums[l];
                if (tm.containsKey(rem)) {
                    tm.put(rem, tm.get(rem)-1);
                    if (tm.get(rem)==0) tm.remove(rem);
                }
                l += 1;
            }
        }

        return count;
    }
}
