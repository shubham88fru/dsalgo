package lc_potd;

import java.util.Arrays;

//@link - https://leetcode.com/problems/single-number-iii/description/
//@check - https://www.youtube.com/watch?v=XAmaiztzDiQ&t=0s&ab_channel=AryanMittal
public class SingleNumberIII {
    public int[] singleNumber(int[] nums) {
        // return usingSort(nums);
        return optimal(nums);
    }

    //1) Optimal solution - using bit manip.
    private int[] optimal(int[] nums) {
        /**
         For the sample input - [a, b, b, c, d, d, e, e]
         if we xor the entire array we'll get a^c (others will ) cancel out.
         not suppose a = 3 and b = 5, then the xor would actually look like,
         a   = 0 1 1
         b   = 1 0 1
         -----------
         a^b = 1 1 0
         -----------

         From the above, we can observe that, the bits where the xor is 1,
         certainly means that one (and exactly one) out of a and b was 1 and
         the other was 0 at that bit position (properties of xor). This means,
         that if we are able to go through the array and find out all the nums
         that have a particular bit set to one (depending on our findings of a^b)
         we are bound to get a set of nums which includes exactly one of a and b
         and the rest will all be couples. And so, if we not xor this new set, we'll
         end up getting one of a and b. and since we have a^b already, we can then get
         the second of a and b as well.
         */
        int xor = 0;
        for (int i=0; i<nums.length; i++) xor ^= nums[i]; //xor of all nums of array.

        int mask = 1;
        while ((mask&xor) == 0) {
            mask = mask << 1; //find a set bit for the mask.
        }

        int a = 0;
        for (int i=0; i<nums.length; i++) {
            //Keep xoring the num sthat satisfy the mask,
            //they will give us a (one half of the ans.)
            if ((mask&nums[i]) != 0) a ^= nums[i];
        }

        //since xor = a ^ b ==> b = xor ^ a
        int b = xor ^ a;

        return new int[] {a, b};
    }


    //2) Better approach - using sorting.
    //But TC is O(nlogn) which is not allowed ATQ.
    private int[] usingSort(int[] nums) {
        Arrays.sort(nums);

        int[] ans = new int[2];
        boolean fFound = false;
        boolean sFound = false;

        int prev = nums[0];
        for (int i=0; i<nums.length-1; i++) {
            if (nums[i] == nums[i+1]) {
                i += 1;
                continue;
            } else {
                if (!fFound) {
                    ans[0] = nums[i];
                    fFound = true;
                } else {
                    ans[1] = nums[i];
                    sFound = true;
                }
            }
        }

        if (!fFound) ans[0] = nums[nums.length-2];
        if (!sFound) ans[1] = nums[nums.length-1];
        return ans;
    }

    //3) Okayish but still not optimal - Using hashmap
    //TC: O(N), but space is also O(N), which is not allowed ATQ.
}
