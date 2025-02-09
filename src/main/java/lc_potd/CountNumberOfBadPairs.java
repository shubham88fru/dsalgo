package lc_potd;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/count-number-of-bad-pairs/
public class CountNumberOfBadPairs {
    public long countBadPairs(int[] nums) {
        // return brute(nums);
        return pass2(nums);
    }

    /*
        This is my soln. Mik had the exact same soln.
        One important concept/learning from this problem -
        whenever we get a question which has some equation
        given between i and j, try taking all equations in
        i and j to respective sides and think about the problem.
     */
    private long pass2(int[] nums) {
        int n = nums.length;
        /*
            transforming the given equation, we get
            j-nums[j] != i-nums[i];

            Therefore, for every j, we need to find out
            how many times we have seen j-nums[j] before.
        */
        long cnt = 0;
        Map<Long, Long> diffFreq = new HashMap<>();

        for (int j=0; j<n; j++) {
            long diff = j-nums[j];
            long freq = diffFreq.getOrDefault(diff, 0l);
            cnt += (j-freq);

            diffFreq.put(diff, diffFreq.getOrDefault(diff, 0l)+1);
        }

        return cnt;
    }

    //TLE
    private long brute(int[] nums) {

        int n = nums.length;
        long cnt = 0;

        for (int i=0; i<n-1; i++) {
            for (int j=i+1; j<n; j++) {
                if (j-i != nums[j]-nums[i]) cnt += 1;
            }
        }

        return cnt;
    }
}
