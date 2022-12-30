package swd.hashmap;

import java.util.HashMap;
import java.util.Map;

//@link - https://practice.geeksforgeeks.org/problems/largest-subarray-with-0-sum/1
public class LargestSubArrayWithZeroSum {
    int maxLen(int arr[], int n) {
        Map<Integer, Integer> mp = new HashMap<Integer, Integer>();
        int prefixSum = 0;
        int maxZeroSumLength = 0;
        mp.put(0, -1); //to handle edge cases like arr=[0] --> result should be 1.
        for (int i=0; i<arr.length; i++) {
            prefixSum += arr[i];
            if (mp.containsKey(prefixSum)) {
                int len = i-mp.get(prefixSum);
                if (len>maxZeroSumLength) maxZeroSumLength = len;
            } else mp.put(prefixSum, i);
        }
        return maxZeroSumLength;
    }
}
