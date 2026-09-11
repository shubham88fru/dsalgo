package lc_potd;

import java.util.HashSet;
import java.util.Set;

//@link - https://leetcode.com/problems/unique-3-digit-even-numbers/?
public class Unique3DigitEvenNumbers {
    public int totalNumbers(int[] digits) {
        return brute(digits);
    }

    /**
        This is bruteforce, for optimal sol
        @see {@link lc_potd.Finding3DigitEvenNumbers}
    */
    private int brute(int[] nums) {
        int n = nums.length, count = 0;
        Set<Integer> st = new HashSet<>();
        for (int i=0; i<n; i++) {
            if (nums[i] == 0) continue;
            for (int j=0; j<n; j++) {
                if (j == i) continue;
                for (int k=0; k<n; k++) {
                    if (k==i || k==j || nums[k]%2 != 0) continue;
                    int num = nums[i]*100 + nums[j]*10 + nums[k];

                    if (!st.contains(num)) count += 1;
                    st.add(num);
                }
            }
        }

        return count;
    }
}
