package lc_potd;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/count-special-triplets/description/?
//@check - https://www.youtube.com/watch?v=WQ05CRcTraY
public class CountSpecialTriplets {
    public int specialTriplets(int[] nums) {
        // return brute(nums);
        // return pass1(nums); -- wrong
        return pass2(nums);
    }

    /**
     This approach came to my mind,
     however, I couldn't think of the
     right way to store the right side
     frequencies properly.
     */
    private int pass2(int[] nums) {
        int n = nums.length;
        Map<Integer, Long> tF = new HashMap<>(); //total freq.
        Map<Integer, Long> lF = new HashMap<>(); //running freq.

        for (int num: nums) tF.put(num, tF.getOrDefault(num, 0l)+1);

        long triplets = 0;
        for (int num: nums) {
            if (num == 0) continue; //edge case of 0.
            long l = lF.getOrDefault(2*num, 0l);

            /**
             Not so clever, but dumb me couldn't see
             this :(
             Right frequency is `total - left`
             */
            long r = tF.getOrDefault(2*num, 0l) - l;

            if (l > 0 && r > 0) {
                triplets = (triplets + l*r)%1000000007;
            }
            lF.put(num, lF.getOrDefault(num, 0l)+1);
        }

        if (tF.containsKey(0) && tF.get(0) >= 3) {
            long m = tF.get(0);
            long nC3 = (m*(m-1)*(m-2)/6);
            triplets = (triplets + nC3)%1000000007;
        }
        return (int)triplets;
    }

    /**
     ---WRONG---
     Simply keeping a track of nums
     won't help coz order of i,j,k matters.
     e.g. of failing test case -
     nums     = [1,1,2,2]
     output   = 1
     expected = 0
     ---WRONG---
     */
    private int pass1(int[] nums) {
        int n = nums.length;
        Map<Double, Integer> mp = new HashMap<>();

        int triplets = 0;
        for (int i=0; i<n; i++) {
            double f = nums[i];
            double s = nums[i]/2.0;
            if (mp.containsKey(f) && mp.containsKey(s)) {
                triplets += Math.min(mp.get(f), mp.get(s));
            }
            mp.put(f, mp.getOrDefault(f, 0)+1);
        }

        return triplets;
    }

    private int brute(int[] nums) {
        int n = nums.length;

        int triplets = 0;
        for (int i=0; i<n; i++) {
            for (int j=i+1; j<n; j++) {
                for (int k=j+1; k<n; k++) {
                    if (nums[k] == 2*nums[j] && nums[i] == 2*nums[j]) triplets += 1;
                }
            }
        }

        return triplets;

    }
}
