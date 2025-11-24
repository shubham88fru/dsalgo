package lc_potd;

import java.util.ArrayList;
import java.util.List;

//@link - https://leetcode.com/problems/binary-prefix-divisible-by-5/?
//@check - https://www.youtube.com/watch?v=dRwEixchYqc
public class BinaryPrefixDivisibleBy5 {
    public List<Boolean> prefixesDivBy5(int[] nums) {
        return mikssol(nums);
    }

    /**
     Based on the simple idea that
     when a binary rep is left shifted,
     the num gets multiplied by 2. This also
     means that if we multiply the num by 2,
     we are effectively left shifting the binary.

     This also goes for dividing the num.
     */
    private List<Boolean> mikssol(int[] nums) {
        int n = nums.length;
        List<Boolean> ans = new ArrayList<>();
        int rem = 0;
        for (int num: nums) {
            /**
             Only keep track of remainder
             to avoid overflow. Explanation
             on why this is okay by mik.
             */
            rem = (rem*2 + num)%5;
            if (rem == 0) ans.add(true);
            else ans.add(false);
        }

        return ans;
    }
}
