package swd.hashmap;

import java.util.HashMap;
import java.util.Map;

//@link - https://practice.geeksforgeeks.org/problems/largest-subarray-of-0s-and-1s/1?utm_source=gfg&utm_medium=article&utm_campaign=bottom_sticky_on_article
public class LargestSubArrayOf0And1 {
    int maxLen(int[] arr, int N){
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int prefixSum = 0;
        int res = 0;
        for (int i=0; i<N; i++) {
            if (arr[i] == 1) prefixSum += 1;
            else prefixSum -= 1;
            if (map.containsKey(prefixSum)) {
                int len = i - map.get(prefixSum);
                if (len>res) res = len;
            } else map.put(prefixSum, i);
        }
        return res;
    }
}
