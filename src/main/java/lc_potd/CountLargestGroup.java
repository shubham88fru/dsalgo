package lc_potd;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/count-largest-group/description/
public class CountLargestGroup {
    public int countLargestGroup(int n) {
        return revise(n);
    }

    private int revise(int n) {
        Map<Integer, Integer> frq = new HashMap<>();
        int maxGroupSz = 1;
        int maxCount = 0;
        for (int i=1; i<=n; i++) {
            int sum = sumDigs(i);
            frq.put(sum, frq.getOrDefault(sum, 0)+1);
            if (frq.get(sum) > maxGroupSz) {
                maxCount = 1;
                maxGroupSz = frq.get(sum);
            } else if (frq.get(sum) == maxGroupSz) {
                maxCount += 1;
            }


        }

        return maxCount;
    }

    private int sumDigs(int n) {
        int sum = 0;
        while (n > 0) {
            sum += (n%10);
            n /= 10;
        }
        return sum;
    }
}
