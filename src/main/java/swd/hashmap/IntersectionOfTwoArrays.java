package swd.hashmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@link - https://leetcode.com/problems/intersection-of-two-arrays/description/
public class IntersectionOfTwoArrays {
    /* My Soln */

    public int[] intersection(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        int len1 = nums1.length;
        int len2 = nums2.length;
        List<Integer> res = new ArrayList<>();

        if (len1 >= len2) {
            for (int j : nums1) {
                if (!map.containsKey(j)) map.put(j, 1);
            }

            for (int j : nums2) {
                if (map.containsKey(j) && map.get(j) != 0) {
                    res.add(j);
                    map.put(j, 0);
                }
            }

            return res.stream().mapToInt(i -> i).toArray();
        }

        for (int j : nums2) {
            if (!map.containsKey(j)) map.put(j, 1);
        }

        for (int j : nums1) {
            if (map.containsKey(j) && map.get(j) != 0) {
                res.add(j);
                map.put(j, 0);
            }
        }

        return res.stream().mapToInt(i -> i).toArray();

    }
}
