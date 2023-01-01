package swd.hashmap;

import java.util.HashMap;
import java.util.Map;

public class SubArraysWithEqual1sAnd0s {
    static int countSubarrWithEqualZeroAndOne(int arr[], int n) {
        // add your code here
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int prefixSum = 0;
        int res = 0;
        for (int el : arr) {
            if (el == 0) prefixSum -= 1;
            else prefixSum += 1;
            if (map.containsKey(prefixSum)) {
                res += map.get(prefixSum);
                map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
            } else map.put(prefixSum, 1);
        }
        return res;
    }
}
