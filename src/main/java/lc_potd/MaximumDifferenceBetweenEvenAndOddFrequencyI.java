package lc_potd;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/maximum-difference-between-even-and-odd-frequency-i/description/
public class MaximumDifferenceBetweenEvenAndOddFrequencyI {
    public int maxDifference(String s) {

        Map<Character, Integer> mp = new HashMap<>();
        int maxOdd = 0;
        int minEven = Integer.MAX_VALUE;

        for (int i=0; i<s.length(); i++) {
            mp.put(s.charAt(i), mp.getOrDefault(s.charAt(i), 0)+1);
        }

        for (Map.Entry<Character, Integer> entry: mp.entrySet()) {
            int freq = entry.getValue();
            if (freq%2 == 0) minEven = Math.min(minEven, freq);
            else maxOdd = Math.max(maxOdd, freq);
        }

        return maxOdd - minEven;
    }
}
